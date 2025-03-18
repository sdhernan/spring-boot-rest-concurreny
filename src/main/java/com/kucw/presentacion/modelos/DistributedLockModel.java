package com.kucw.presentacion.modelos;

import java.util.Date;

public class DistributedLockModel {

	private Long idBloqueoDistribuido;

	private String llaveBloqueo;

	private String procesoBloqueo;

	private Date fechaInicioBloqueo;

	private Date fechaExpiraBloqueo;

	private String usuarioModificador;

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
