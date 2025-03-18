package com.kucw.presentacion.repositorios;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kucw.presentacion.entidades.DistributedLock;

@Repository
public interface DistributedLockRepository extends JpaRepository<DistributedLock, Long> {

	/**
	 * Elimina todos los bloqueos expirados
	 */
	@Modifying
	@Query("DELETE FROM DistributedLock l WHERE l.fechaExpiraBloqueo <= :now")
	void cleanExpiredLocks(@Param("now") Date now);

	/**
	 * Elimina un bloqueo por su clave única y propietario
	 */
	@Modifying
	@Query("DELETE FROM DistributedLock l WHERE l.llaveBloqueo = :llaveBloqueo AND l.procesoBloqueo = :procesoBloqueo")
	void deleteByLlaveBloqueoAndProcesoBloqueo(@Param("llaveBloqueo") String llaveBloqueo,
			@Param("procesoBloqueo") String procesoBloqueo);

	/**
	 * Busca un bloqueo por su clave única
	 */
	Optional<DistributedLock> findByLlaveBloqueo(String llaveBloqueo);

	/**
	 * Busca un bloqueo válido (no expirado) por su clave
	 */
	@Query("SELECT l FROM DistributedLock l WHERE l.llaveBloqueo = :key AND l.fechaExpiraBloqueo > :now")
	Optional<DistributedLock> findValidLock(@Param("key") String key, @Param("now") Date now);
}
