package com.kucw.presentacion.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_NSAR_BLOQUEO_DISTRIBUIDO", indexes = {
		@Index(name = "IDX_LLAVE_BLOQUEO", columnList = "CH_LLAVE_BLOQUEO", unique = true),
		@Index(name = "IDX_FECHA_EXPIRACION", columnList = "FC_EXPIRA_BLOQUEO") })
public class DistributedLock {

	@Id
	@Column(name = "ID_BLOQUEO_DISTRIBUIDO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bloqueoSequence")
	@SequenceGenerator(name = "bloqueoSequence", sequenceName = "SEQ_TB_NSAR_BLOQUEO_DISTRIBUIDO", allocationSize = 1)
	private Long idBloqueoDistribuido;

	@Column(name = "CH_LLAVE_BLOQUEO", length = 255, nullable = false, unique = true)
	private String llaveBloqueo;

	@Column(name = "CH_PROCESO_BLOQUEO", length = 100, nullable = false)
	private String procesoBloqueo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FC_INICIO_BLOQUEO", nullable = false)
	private Date fechaInicioBloqueo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FC_EXPIRA_BLOQUEO", nullable = false)
	private Date fechaExpiraBloqueo;

	@Column(name = "CH_USUARIO_MODIFICADOR", length = 50)
	private String usuarioModificador;

	@Column(name = "CH_NOMBRE_SERVICIO", length = 100)
	private String nombreServicio;

	public Date getFechaExpiraBloqueo() {
		return fechaExpiraBloqueo;
	}

	public Date getFechaInicioBloqueo() {
		return fechaInicioBloqueo;
	}

	// Getters y Setters
	public Long getIdBloqueoDistribuido() {
		return idBloqueoDistribuido;
	}

	public String getLlaveBloqueo() {
		return llaveBloqueo;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public String getProcesoBloqueo() {
		return procesoBloqueo;
	}

	public String getUsuarioModificador() {
		return usuarioModificador;
	}

	public void setFechaExpiraBloqueo(Date fechaExpiraBloqueo) {
		this.fechaExpiraBloqueo = fechaExpiraBloqueo;
	}

	public void setFechaInicioBloqueo(Date fechaInicioBloqueo) {
		this.fechaInicioBloqueo = fechaInicioBloqueo;
	}

	public void setIdBloqueoDistribuido(Long idBloqueoDistribuido) {
		this.idBloqueoDistribuido = idBloqueoDistribuido;
	}

	public void setLlaveBloqueo(String llaveBloqueo) {
		this.llaveBloqueo = llaveBloqueo;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public void setProcesoBloqueo(String procesoBloqueo) {
		this.procesoBloqueo = procesoBloqueo;
	}

	public void setUsuarioModificador(String usuarioModificador) {
		this.usuarioModificador = usuarioModificador;
	}
}
