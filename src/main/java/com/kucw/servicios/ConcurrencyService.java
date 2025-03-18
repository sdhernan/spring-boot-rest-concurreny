package com.kucw.servicios;

import java.util.UUID;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kucw.presentacion.excepciones.ConcurrencyException;

/**
 * Servicio de alto nivel que proporciona funcionalidades para ejecutar
 * operaciones con control de concurrencia distribuida.
 * 
 * Esta clase implementa mecanismos para garantizar la ejecución segura de operaciones
 * en entornos distribuidos mediante un sistema de bloqueos. Permite ejecutar código
 * crítico asegurando que solo un proceso puede acceder al mismo recurso simultáneamente,
 * evitando condiciones de carrera y problemas de concurrencia.
 * 
 * El servicio utiliza un identificador único para cada recurso y un sistema de bloqueos
 * distribuidos gestionado por el componente LockManagerService.
 */
@Service
public class ConcurrencyService {

	/** 
	 * Prefijo utilizado para crear identificadores temporales durante las verificaciones
	 * de bloqueo. Este prefijo ayuda a distinguir las operaciones de verificación de las
	 * operaciones normales de bloqueo.
	 */
	private static final String PREFIJO_CHECK = "check-";

	/** 
	 * Servicio para la gestión de bloqueos distribuidos.
	 * Este componente es responsable de la adquisición y liberación de bloqueos
	 * en un entorno distribuido, permitiendo la sincronización entre múltiples instancias
	 * o nodos de la aplicación.
	 */
	@Autowired
	private LockManagerService lockManager;

	/**
	 * Ejecuta una acción con bloqueo distribuido, garantizando exclusión mutua.
	 * 
	 * Este método implementa un patrón de ejecución protegida donde:
	 * 1. Se intenta adquirir un bloqueo para el recurso especificado
	 * 2. Si se obtiene el bloqueo, se ejecuta la acción proporcionada
	 * 3. Al finalizar, se libera el bloqueo independientemente del resultado
	 * 
	 * El método realiza hasta 3 intentos para adquirir el bloqueo, con una pausa
	 * de 500ms entre cada intento, para manejar situaciones de alta concurrencia.
	 *
	 * @param <T>        Tipo de retorno de la acción a ejecutar
	 * @param resourceId Identificador único del recurso a bloquear. Debe ser consistente
	 *                   para el mismo recurso en todas las instancias de la aplicación
	 * @param action     Función a ejecutar mientras se tiene el bloqueo. Esta función
	 *                   se ejecutará de forma atómica respecto al recurso especificado
	 * @return Resultado de la acción ejecutada
	 * @throws ConcurrencyException si no se puede adquirir el bloqueo después de los reintentos
	 *                             o si ocurre una interrupción durante la espera
	 */
	public <T> T executeWithLock(String resourceId, Supplier<T> action) {
		// Generamos un identificador único para este proceso específico
		// Esto permite identificar quién tiene el bloqueo y evita que otros procesos lo liberen
		String processId = UUID.randomUUID().toString();
		boolean lockAcquired = false;

		try {
			// Bucle de reintentos: intentamos obtener el bloqueo hasta 3 veces
			// Esto aumenta la probabilidad de éxito en situaciones de alta concurrencia
			for (int i = 0; i < 3; i++) {
				// Intentamos adquirir el bloqueo para el recurso con nuestro ID de proceso
				lockAcquired = lockManager.acquireLock(resourceId, processId);
				
				// Si logramos adquirir el bloqueo, salimos del bucle de reintentos
				if (lockAcquired) {
					break;
				}
				
				// Si no pudimos adquirir el bloqueo, esperamos 500ms antes de reintentar
				// Esta pausa reduce la contención y permite que otros procesos liberen el bloqueo
				Thread.sleep(500);
			}

			// Si después de los reintentos no pudimos adquirir el bloqueo,
			// lanzamos una excepción indicando el problema
			if (!lockAcquired) {
				throw new ConcurrencyException(
						StringUtils.join("No se pudo obtener el bloqueo para el recurso: ", resourceId));
			}

			// En este punto tenemos el bloqueo adquirido, por lo que podemos
			// ejecutar la acción proporcionada de forma segura
			// El método get() de Supplier ejecuta la función y devuelve su resultado
			return action.get();

		} catch (InterruptedException e) {
			// Si el hilo es interrumpido mientras espera, restauramos la bandera de interrupción
			// para que los llamadores de nivel superior puedan detectarla y manejarla adecuadamente
			Thread.currentThread().interrupt();
			
			// Propagamos la interrupción como una excepción de concurrencia
			throw new ConcurrencyException("Interrupción mientras se esperaba el bloqueo", e);
		} finally {
			// El bloque finally garantiza que siempre intentaremos liberar el bloqueo
			// si lo hemos adquirido, incluso si ocurre una excepción durante la ejecución
			// de la acción. Esto previene bloqueos permanentes (deadlocks)
			if (lockAcquired) {
				lockManager.releaseLock(resourceId, processId);
			}
		}
	}

	/**
	 * Verifica si un recurso está actualmente bloqueado sin intentar adquirir un bloqueo permanente.
	 * 
	 * Este método realiza una verificación no intrusiva del estado de bloqueo de un recurso
	 * intentando adquirir temporalmente el bloqueo y liberándolo inmediatamente si tiene éxito.
	 * Es útil para realizar comprobaciones previas antes de intentar operaciones que requieren
	 * bloqueo, o para mostrar información de estado en interfaces de usuario.
	 *
	 * @param resourceId Identificador único del recurso a verificar
	 * @return true si el recurso está actualmente bloqueado por otro proceso,
	 *         false si el recurso está disponible (no bloqueado)
	 */
	public boolean isResourceLocked(String resourceId) {
		// Creamos un ID temporal único para esta verificación
		// El prefijo ayuda a identificar que es una operación de verificación
		String tempId = StringUtils.join(PREFIJO_CHECK, UUID.randomUUID().toString());

		// Intentamos adquirir el bloqueo con el ID temporal
		// Si podemos adquirirlo, significa que el recurso no estaba bloqueado
		boolean canAcquire = lockManager.acquireLock(resourceId, tempId);

		// Si logramos adquirir el bloqueo, debemos liberarlo inmediatamente
		// ya que solo estamos verificando el estado, no realizando una operación
		if (canAcquire) {
			// Liberamos el bloqueo que acabamos de adquirir
			lockManager.releaseLock(resourceId, tempId);
			
			// Devolvemos false indicando que el recurso NO está bloqueado
			return false;
		}

		// Si no pudimos adquirir el bloqueo, significa que el recurso
		// está actualmente bloqueado por otro proceso
		return true;
	}
}