package com.kucw.exposicion.controladores;

import java.time.LocalDate;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kucw.presentacion.modelos.ValidarSolicitudCertificacionAforeEntrada;
import com.kucw.presentacion.modelos.ValidarSolicitudCertificacionAforeSalida;
import com.kucw.servicios.LockManagerService;
import com.kucw.servicios.NotificacionesService;
import com.kucw.servicios.ValidarDiasHabilService;
import com.kucw.servicios.ValidarSolicitudCertificacionAforeService;

/**
 * Controlador REST que expone servicios para la validación de solicitudes de certificación AFORE.
 * 
 * Esta clase implementa endpoints para validar solicitudes de certificación relacionadas con AFORE,
 * incluyendo mecanismos para prevenir procesamiento de peticiones duplicadas mediante un sistema de bloqueo
 * basado en hash MD5 de la petición entrante.
 * 
 * El servicio principal verifica si el día es hábil antes de procesar la solicitud y notifica
 * los resultados a través del servicio de notificaciones.
 */
@RestController
public class ValidarSolicitudCertificacionAforeRestService {

	/** Logger para registro de eventos y errores. */
	private static final Logger logger = LoggerFactory.getLogger(ValidarSolicitudCertificacionAforeRestService.class);

	/** 
	 * Prefijo utilizado para las claves de bloqueo en el sistema de prevención de duplicados.
	 * Este prefijo se concatena con el hash MD5 de la petición para formar la clave de bloqueo.
	 */
	private static final String PREFIJO_CERTIFICACION = "certificacion:";

	/** 
	 * Servicio encargado de la lógica de negocio para validar solicitudes de certificación AFORE.
	 * Procesa la información de entrada y determina si la solicitud cumple con los requisitos.
	 */
	@Autowired
	private ValidarSolicitudCertificacionAforeService validarSolicitudCertificacionAforeService;

	/** 
	 * Servicio para validar si un día es hábil según el calendario establecido.
	 * Se utiliza para determinar si se debe procesar la solicitud en la fecha actual.
	 */
	@Autowired
	private ValidarDiasHabilService validarDiasHabilService;

	/** 
	 * Servicio para enviar notificaciones relacionadas con el resultado del procesamiento
	 * de solicitudes de certificación AFORE.
	 */
	@Autowired
	private NotificacionesService notificacionesService;

	/** 
	 * Servicio para gestionar bloqueos que previenen el procesamiento simultáneo 
	 * de peticiones idénticas, implementando un mecanismo de concurrencia distribuida.
	 */
	@Autowired
	private LockManagerService lockManagerService;

	/** 
	 * Componente para serialización y deserialización de objetos JSON.
	 * Se utiliza para convertir los objetos de entrada a formato JSON para generar el hash
	 * que identifica peticiones duplicadas.
	 */
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Endpoint de verificación para comprobar la disponibilidad del servicio.
	 * 
	 * Este método simple devuelve "OK" cuando el servicio está funcionando correctamente.
	 * Puede ser utilizado para pruebas de salud (health checks) o verificaciones de disponibilidad.
	 *
	 * @return Cadena "OK" indicando que el servicio está disponible
	 */
	@GetMapping(value = "/valida", produces = { MediaType.TEXT_HTML_VALUE })
	public String buscarCurp() {
		return "OK";
	}

	/**
	 * Procesa y valida una solicitud de certificación AFORE.
	 * 
	 * Este método implementa la lógica principal para validar solicitudes de certificación AFORE.
	 * El proceso incluye:
	 * - Verificación de peticiones duplicadas mediante hash MD5 del objeto de entrada
	 * - Adquisición de un bloqueo distribuido para evitar procesamiento simultáneo de solicitudes idénticas
	 * - Validación de día hábil para el procesamiento mediante el servicio ValidarDiasHabilService
	 * - Procesamiento de la solicitud a través del servicio ValidarSolicitudCertificacionAforeService
	 * - Notificación de resultados mediante el servicio NotificacionesService
	 * - Manejo de excepciones con respuestas de error apropiadas
	 * - Liberación del bloqueo adquirido al finalizar el procesamiento
	 *
	 * El método utiliza un mecanismo de control de concurrencia basado en bloqueos distribuidos
	 * para evitar el procesamiento duplicado de la misma solicitud. Esto se logra generando un
	 * hash MD5 del objeto de entrada serializado a JSON y utilizando este hash como clave para
	 * el bloqueo en el servicio LockManagerService.
	 *
	 * Si el día no es hábil según el servicio ValidarDiasHabilService (código P00020), la solicitud
	 * no se procesa y se devuelve un código de error específico.
	 *
	 * @param entrada Objeto que contiene los datos de la solicitud a validar. Incluye información
	 *                como tipo de prestación, datos del solicitante y detalles específicos de la certificación.
	 *                Este objeto se serializa a JSON para generar un hash único que identifica la petición.
	 * 
	 * @return Objeto de respuesta con el resultado de la validación, incluyendo:
	 *         - resultadoOperacion: "01" para operación exitosa, "02" para operación fallida
	 *         - diagnosticoProcesar: Código de diagnóstico específico
	 *           * "532": Error por día no hábil o petición duplicada
	 *           * "999": Error general en la aplicación
	 *           * Otros códigos según la lógica de negocio implementada en el servicio
	 *         - tipoPrestacion: El mismo valor recibido en el objeto de entrada
	 *         - Otros campos específicos según el resultado de la validación
	 */
	@ResponseBody
	@PostMapping(value = "disposicionimss/certificacion", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ValidarSolicitudCertificacionAforeSalida validarSolicitudCertificacionAfore(
			@RequestBody ValidarSolicitudCertificacionAforeEntrada entrada) {

		logger.info("Entrada validarSolicitudCertificacionAfore: {}", entrada);

		ValidarSolicitudCertificacionAforeSalida salida;
		String peticionKey = null;
		String processId = null;
		boolean lockAcquired = false;

		try {

			// Generar hash del objeto de entrada para detectar peticiones idénticas
			try {

				String jsonContent = objectMapper.writeValueAsString(entrada);
				String hashContent = DigestUtils.md5Hex(jsonContent);

				peticionKey = StringUtils.join(PREFIJO_CERTIFICACION, hashContent);
				processId = UUID.randomUUID().toString();

				// Intentar adquirir el bloqueo
				lockAcquired = lockManagerService.acquireLock(peticionKey, processId);

				if (!lockAcquired) {

					// Si no se pudo adquirir el bloqueo, significa que una petición idéntica está
					// en proceso
					salida = new ValidarSolicitudCertificacionAforeSalida();
					salida.setResultadoOperacion("02");
					salida.setDiagnosticoProcesar("532");
					salida.setTipoPrestacion(entrada.getTipoPrestacion());
					return salida;

				}

			} catch (JsonProcessingException e) {

				logger.error("Error al procesar JSON para validación de duplicados", e);
				// Continuamos con el proceso normal si falla la detección de duplicados
			}

			// Lógica original del método
			String validarDiaHabil = validarDiasHabilService.obtenerDiaHabil(LocalDate.now(), "P00020");
			if ("1".equals(validarDiaHabil)) {
				salida = validarSolicitudCertificacionAforeService.validarSolicitudCertificacionAfore(entrada);
				notificacionesService.notificarServicioAfore(entrada, salida);
			} else {
				salida = new ValidarSolicitudCertificacionAforeSalida();
				salida.setResultadoOperacion("02");
				salida.setDiagnosticoProcesar("532");
				salida.setTipoPrestacion(entrada.getTipoPrestacion());
			}
		} catch (Exception e) {

			logger.error("error en la aplicación", e);
			salida = new ValidarSolicitudCertificacionAforeSalida();
			salida.setResultadoOperacion("02");
			salida.setDiagnosticoProcesar("999");
			salida.setTipoPrestacion(entrada.getTipoPrestacion());

		} finally {

			// Liberar el bloqueo si fue adquirido
			if (lockAcquired && peticionKey != null && processId != null) {
				try {
					lockManagerService.releaseLock(peticionKey, processId);
				} catch (Exception e) {
					logger.error("Error al liberar bloqueo", e);
				}
			}

		}

		return salida;
	}
}
