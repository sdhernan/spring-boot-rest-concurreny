package com.kucw.servicios;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.kucw.presentacion.entidades.DistributedLock;
import com.kucw.presentacion.repositorios.DistributedLockRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio que implementa un mecanismo de bloqueo distribuido basado en base de datos.
 * 
 * Esta clase proporciona funcionalidades para gestionar bloqueos distribuidos utilizando
 * una tabla en base de datos como mecanismo de sincronización. Este enfoque permite
 * coordinar el acceso a recursos compartidos entre múltiples instancias o nodos de
 * la aplicación, garantizando la integridad y consistencia de los datos.
 * 
 * El servicio implementa operaciones para:
 * - Adquirir bloqueos con un tiempo de expiración configurable
 * - Liberar bloqueos de forma segura
 * - Limpiar automáticamente bloqueos expirados
 * - Identificar el servicio y usuario que realiza las operaciones
 * 
 * Los bloqueos son identificados por una clave única (resourceId) y un identificador
 * de proceso (processId) que permite validar que solo el proceso que adquirió el bloqueo
 * pueda liberarlo.
 */
@Service
public class LockManagerService {

	private static final Logger logger = LoggerFactory.getLogger(LockManagerService.class);

	/**
	 * Repositorio para acceder a la entidad DistributedLock en la base de datos.
	 * Este componente proporciona las operaciones CRUD y consultas personalizadas
	 * necesarias para implementar el mecanismo de bloqueo.
	 */
	@Autowired
	private DistributedLockRepository lockRepository;

	/**
	 * Tiempo de expiración de los bloqueos en segundos.
	 * Este valor determina cuánto tiempo permanecerá activo un bloqueo antes de
	 * considerarse expirado y poder ser eliminado automáticamente.
	 * Se configura mediante la propiedad lock.timeout.seconds con un valor predeterminado de 30 segundos.
	 */
	@Value("${lock.timeout.seconds:30}")
	private int lockTimeoutSeconds;

	/**
	 * Nombre del servicio que está utilizando el gestor de bloqueos.
	 * Este valor se registra en cada bloqueo creado para identificar qué servicio
	 * o componente de la aplicación ha adquirido el bloqueo.
	 * Se configura mediante la propiedad application.service.name con un valor predeterminado de "defaultService".
	 */
	@Value("${application.service.name:defaultService}")
	private String serviceName;

	/**
	 * Usuario predeterminado que se registra como creador/modificador del bloqueo.
	 * Este valor se utiliza cuando no se especifica explícitamente un usuario mediante
	 * el método setCurrentUser.
	 * Se configura mediante la propiedad application.user.default con un valor predeterminado de "SYSTEM".
	 */
	@Value("${application.user.default:SYSTEM}")
	private String defaultUser;

	/**
	 * Intenta adquirir un bloqueo distribuido para un recurso específico.
	 * 
	 * Este método implementa el siguiente algoritmo para garantizar la exclusión mutua:
	 * 1. Limpia los bloqueos expirados para evitar deadlocks
	 * 2. Intenta crear un nuevo bloqueo con un tiempo de expiración
	 * 
	 * La operación se ejecuta con aislamiento SERIALIZABLE para garantizar
	 * la consistencia en entornos altamente concurrentes. Esto evita condiciones
	 * de carrera donde múltiples procesos podrían adquirir el mismo bloqueo
	 * simultáneamente.
	 * 
	 * @param resourceId Identificador único del recurso que se desea bloquear.
	 *                   Debe ser consistente en todas las instancias de la aplicación.
	 * @param processId  Identificador único del proceso que solicita el bloqueo.
	 *                   Típicamente un UUID generado para cada operación.
	 * @param requestJson Contenido JSON de la petición que se está procesando.
	 *                   Este valor se guardará en el campo request cuando se detecte
	 *                   una petición simultánea.
	 * @return true si el bloqueo fue adquirido exitosamente, false si el recurso
	 *         ya está bloqueado por otro proceso o si ocurrió un error al intentar
	 *         adquirir el bloqueo.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean acquireLock(String resourceId, String processId, String requestJson) {
		// Obtenemos la fecha y hora actual para registrar cuándo se adquiere el bloqueo
		Date now = new Date();
		// Calculamos la fecha y hora de expiración sumando el tiempo de timeout configurado
		Date expiryTime = new Date(now.getTime() + (lockTimeoutSeconds * 1000));

		// Limpiamos los bloqueos expirados antes de intentar adquirir uno nuevo
		// Esto evita acumulación de registros obsoletos y mejora el rendimiento
		lockRepository.cleanExpiredLocks(now);

		try {
			// Creamos un nuevo objeto de bloqueo con los datos necesarios
			DistributedLock lock = new DistributedLock();
			lock.setLlaveBloqueo(resourceId);      // Identificador del recurso bloqueado
			lock.setProcesoBloqueo(processId);     // Identificador del proceso que adquiere el bloqueo
			lock.setFechaInicioBloqueo(now);       // Momento en que se adquiere el bloqueo
			lock.setFechaExpiraBloqueo(expiryTime); // Momento en que expirará el bloqueo
			lock.setUsuarioModificador(defaultUser); // Usuario que realiza la operación
			lock.setNombreServicio(serviceName);    // Servicio que adquiere el bloqueo
			lock.setPeticionSimultanea("FALSE");      // Por defecto, no es una petición simultánea
			lock.setRequest(requestJson);           // Guardamos el JSON de la petición

			// Guardamos el bloqueo en la base de datos
			// Esta operación fallará con DataIntegrityViolationException si otro proceso
			// ya ha creado un bloqueo con el mismo resourceId (debido a la restricción de unicidad)
			lockRepository.save(lock);
			return true; // Bloqueo adquirido exitosamente
		} catch (DataIntegrityViolationException e) {
			// Si se produce una violación de integridad, significa que ya existe un bloqueo para este recurso
			try {
				// Actualizamos el bloqueo existente para marcar que se detectó una petición simultánea
				lockRepository.updateLockWithSimultaneousRequest(resourceId);
			} catch (Exception updateEx) {
				// Si falla la actualización, simplemente lo registramos pero continuamos con el proceso
				logger.error("Error al actualizar el bloqueo con petición simultánea: " + updateEx.getMessage(), updateEx);
			}
			return false; // No se pudo adquirir el bloqueo
		} catch (Exception e) {
			// Capturamos cualquier otra excepción que pueda ocurrir durante el proceso
			// Esto nos permite manejar casos no previstos sin interrumpir la ejecución
			// En un entorno de producción, sería recomendable registrar esta excepción en logs
			return false; // No se pudo adquirir el bloqueo debido a un error inesperado
		}
	}
	
	/**
	 * Intenta adquirir un bloqueo distribuido para un recurso específico.
	 * Sobrecarga del método acquireLock que no requiere el parámetro requestJson.
	 * 
	 * @param resourceId Identificador único del recurso que se desea bloquear.
	 * @param processId  Identificador único del proceso que solicita el bloqueo.
	 * @return true si el bloqueo fue adquirido exitosamente, false si el recurso
	 *         ya está bloqueado por otro proceso o si ocurrió un error.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean acquireLock(String resourceId, String processId) {
		return acquireLock(resourceId, processId, resourceId);
	}

	/**
	 * Tarea programada que elimina periódicamente los bloqueos expirados de la base de datos.
	 * 
	 * Este método se ejecuta automáticamente a intervalos regulares definidos por la
	 * propiedad lock.cleanup.interval.ms (valor predeterminado: 300000 ms = 5 minutos).
	 * Su propósito es mantener limpia la tabla de bloqueos, eliminando aquellos que
	 * han superado su tiempo de expiración y ya no son válidos.
	 * 
	 * La limpieza periódica es crucial para:
	 * - Evitar la acumulación de registros obsoletos en la base de datos
	 * - Liberar recursos que podrían haber quedado bloqueados por fallos en la aplicación
	 * - Mejorar el rendimiento general del sistema de bloqueos
	 * 
	 * Este método es transaccional para garantizar la consistencia de la operación.
	 */
	@Scheduled(fixedRateString = "${lock.cleanup.interval.ms:300000}")
	@Transactional
	public void cleanupExpiredLocks() {
		// Eliminamos todos los bloqueos cuya fecha de expiración es anterior a la fecha actual
		lockRepository.cleanExpiredLocks(new Date());
	}

	/**
	 * Libera un bloqueo previamente adquirido por un proceso específico.
	 * 
	 * Este método implementa un mecanismo seguro para liberar bloqueos, verificando
	 * que solo el proceso que adquirió el bloqueo pueda liberarlo. Esto previene
	 * situaciones donde un proceso podría liberar accidentalmente el bloqueo
	 * adquirido por otro.
	 * 
	 * El método realiza las siguientes operaciones:
	 * 1. Busca el bloqueo asociado al recurso especificado
	 * 2. Verifica que el processId coincida con el que adquirió el bloqueo
	 * 3. Si coincide, elimina el bloqueo de la base de datos
	 * 
	 * La operación es transaccional para garantizar la atomicidad y consistencia.
	 * 
	 * @param resourceId Identificador único del recurso cuyo bloqueo se desea liberar
	 * @param processId  Identificador del proceso que intenta liberar el bloqueo.
	 *                   Debe coincidir con el processId que adquirió originalmente el bloqueo.
	 */
	@Transactional
	public void releaseLock(String resourceId, String processId) {
		// Buscamos el bloqueo por su identificador de recurso
		lockRepository.findByLlaveBloqueo(resourceId).ifPresent(lock -> {
			// Verificamos que el proceso que intenta liberar el bloqueo sea el mismo que lo adquirió
			if (lock.getProcesoBloqueo().equals(processId)) {
				// Si coincide, eliminamos el bloqueo de la base de datos
				lockRepository.delete(lock);
			}
			// Si no coincide, no hacemos nada (el bloqueo permanece)
		});
	}

	/**
	 * Establece el usuario modificador para los bloqueos creados por este servicio.
	 * 
	 * Este método permite personalizar el usuario que se registrará como creador/modificador
	 * de los bloqueos adquiridos posteriormente. Es útil para identificar qué usuario
	 * o proceso de negocio está realizando operaciones que requieren bloqueos.
	 * 
	 * @param username Nombre del usuario que está realizando la operación.
	 *                 Este valor se registrará en el campo usuarioModificador
	 *                 de los bloqueos creados después de llamar a este método.
	 */
	public void setCurrentUser(String username) {
		this.defaultUser = username;
	}
}
