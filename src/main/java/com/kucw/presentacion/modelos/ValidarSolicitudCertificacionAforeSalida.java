package com.kucw.presentacion.modelos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class ValidarSolicitudCertificacionAforeSalida.
 *
 * @author jmcabrer
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidarSolicitudCertificacionAforeSalida implements Serializable {

	/** serial id. */
	private static final long serialVersionUID = -4662711272227864100L;

	/** resultado Operacion. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String resultadoOperacion;

	/** diagnostico Cuenta Individual. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String diagnosticoProcesar;

	/** entidad Origen. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String entidadOrigen;

	/** tipo De Tramite. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String tipoTramite;

	/** nss. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String nss;

	/** curp. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String curp;

	/** nombre Trabajador Imss. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String nombreTrabajadorImss;

	/** nombre Trabajador Procanase. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String nombreTrabajadorProcanase;

	/** nombre Trabajador. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String nombreTrabajador;

	/** apellido Paterno. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String apellidoPaterno;

	/** apellido Materno. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String apellidoMaterno;

	/** tipo De Prestacion. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String tipoPrestacion;

	/** fecha Matrimonio Desempleo. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String fechaMatrimonioDesempleo;

	/** fecha Notificacion Imss. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String fechaNotificacionImss;

	/** fecha Conclusion Vigencia. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String fechaConclusionVigencia;

	/** numero Resolucion. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String numeroResolucion;

	/** tipo Trabajador Aportacion Cuota Social. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String tipoTrabAportacionCuotaSocial;

	/** sbc TipoA. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String sbcTipoA;

	/** sbcT ipoB. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String sbcTipoB;

	/** idPago Complementario. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String idPagoComplementario;

	/** monto Pagado Retiro Original. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String montoPagadoRetiroOriginal;

	/** saldo Anterior Pago Retir oOriginal. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String saldoAnteriorPagoRetiroOriginal;

	/** folio Operacion IMSS. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String folioOperacionIMSS;

	/** delegacion. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String delegacion;

	/** subdelegacion. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String subdelegacion;

	/** umf. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String umf;

	/** fecha Prest Tramite. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String fechaPrestTramite;

	/** fecha Baja Trabajador. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String fechaBajaTrabajador;

	/** numero Consecutivo Procesar. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String numeroConsecutivoProcesar;

	/** total Resoluciones Procesar. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String totalResolucionesProcesar;

	/** clave Admin Actual. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String claveAdminActual;

	/** origen. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String origen;

	/** idSolicitante. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String idSolicitante;

	/** curp Solicitante. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String curpSolicitante;

	/** sello Trabajador. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String selloTrabajador;

	/** curp Agente Servicio. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String curpAgenteServicio;

	/**
	 * Gets the apellido materno.
	 *
	 * @return el atributo apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * Gets the apellido paterno.
	 *
	 * @return el atributo apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * Gets the clave admin actual.
	 *
	 * @return el atributo claveAdminActual
	 */
	public String getClaveAdminActual() {
		return claveAdminActual;
	}

	/**
	 * Gets the curp.
	 *
	 * @return el atributo curp
	 */
	public String getCurp() {
		return curp;
	}

	/**
	 * Gets the curp agente servicio.
	 *
	 * @return el atributo curpAgenteServicio
	 */
	public String getCurpAgenteServicio() {
		return curpAgenteServicio;
	}

	/**
	 * Gets the curp solicitante.
	 *
	 * @return el atributo curpSolicitante
	 */
	public String getCurpSolicitante() {
		return curpSolicitante;
	}

	/**
	 * Gets the delegacion.
	 *
	 * @return el atributo delegacion
	 */
	public String getDelegacion() {
		return delegacion;
	}

	/**
	 * Gets the diagnostico procesar.
	 *
	 * @return el atributo diagnosticoProcesar
	 */
	public String getDiagnosticoProcesar() {
		return diagnosticoProcesar;
	}

	/**
	 * Gets the entidad origen.
	 *
	 * @return el atributo entidadOrigen
	 */
	public String getEntidadOrigen() {
		return entidadOrigen;
	}

	/**
	 * Gets the fecha baja trabajador.
	 *
	 * @return el atributo fechaBajaTrabajador
	 */
	public String getFechaBajaTrabajador() {
		return fechaBajaTrabajador;
	}

	/**
	 * Gets the fecha conclusion vigencia.
	 *
	 * @return el atributo fechaConclusionVigencia
	 */
	public String getFechaConclusionVigencia() {
		return fechaConclusionVigencia;
	}

	/**
	 * Gets the fecha matrimonio desempleo.
	 *
	 * @return el atributo fechaMatrimonioDesempleo
	 */
	public String getFechaMatrimonioDesempleo() {
		return fechaMatrimonioDesempleo;
	}

	/**
	 * Gets the fecha notificacion imss.
	 *
	 * @return el atributo fechaNotificacionImss
	 */
	public String getFechaNotificacionImss() {
		return fechaNotificacionImss;
	}

	/**
	 * Gets the fecha prest tramite.
	 *
	 * @return el atributo fechaPrestTramite
	 */
	public String getFechaPrestTramite() {
		return fechaPrestTramite;
	}

	/**
	 * Gets the folio operacion IMSS.
	 *
	 * @return el atributo folioOperacionIMSS
	 */
	public String getFolioOperacionIMSS() {
		return folioOperacionIMSS;
	}

	/**
	 * Gets the id pago complementario.
	 *
	 * @return el atributo idPagoComplementario
	 */
	public String getIdPagoComplementario() {
		return idPagoComplementario;
	}

	/**
	 * Gets the id solicitante.
	 *
	 * @return el atributo idSolicitante
	 */
	public String getIdSolicitante() {
		return idSolicitante;
	}

	/**
	 * Gets the monto pagado retiro original.
	 *
	 * @return el atributo montoPagadoRetiroOriginal
	 */
	public String getMontoPagadoRetiroOriginal() {
		return montoPagadoRetiroOriginal;
	}

	/**
	 * Gets the nombre trabajador.
	 *
	 * @return el atributo nombreTrabajador
	 */
	public String getNombreTrabajador() {
		return nombreTrabajador;
	}

	/**
	 * Gets the nombre trabajador imss.
	 *
	 * @return el atributo nombreTrabajadorImss
	 */
	public String getNombreTrabajadorImss() {
		return nombreTrabajadorImss;
	}

	/**
	 * Gets the nombre trabajador procanase.
	 *
	 * @return el atributo nombreTrabajadorProcanase
	 */
	public String getNombreTrabajadorProcanase() {
		return nombreTrabajadorProcanase;
	}

	/**
	 * Gets the nss.
	 *
	 * @return el atributo nss
	 */
	public String getNss() {
		return nss;
	}

	/**
	 * Gets the numero consecutivo procesar.
	 *
	 * @return el atributo numeroConsecutivoProcesar
	 */
	public String getNumeroConsecutivoProcesar() {
		return numeroConsecutivoProcesar;
	}

	/**
	 * Gets the numero resolucion.
	 *
	 * @return el atributo numeroResolucion
	 */
	public String getNumeroResolucion() {
		return numeroResolucion;
	}

	/**
	 * Gets the origen.
	 *
	 * @return el atributo origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Gets the resultado operacion.
	 *
	 * @return el atributo resultadoOperacion
	 */
	public String getResultadoOperacion() {
		return resultadoOperacion;
	}

	/**
	 * Gets the saldo anterior pago retiro original.
	 *
	 * @return el atributo saldoAnteriorPagoRetiroOriginal
	 */
	public String getSaldoAnteriorPagoRetiroOriginal() {
		return saldoAnteriorPagoRetiroOriginal;
	}

	/**
	 * Gets the sbc tipo A.
	 *
	 * @return el atributo sbcTipoA
	 */
	public String getSbcTipoA() {
		return sbcTipoA;
	}

	/**
	 * Gets the sbc tipo B.
	 *
	 * @return el atributo sbcTipoB
	 */
	public String getSbcTipoB() {
		return sbcTipoB;
	}

	/**
	 * Gets the sello trabajador.
	 *
	 * @return el atributo selloTrabajador
	 */
	public String getSelloTrabajador() {
		return selloTrabajador;
	}

	/**
	 * Gets the subdelegacion.
	 *
	 * @return el atributo subdelegacion
	 */
	public String getSubdelegacion() {
		return subdelegacion;
	}

	/**
	 * Gets the tipo prestacion.
	 *
	 * @return el atributo tipoPrestacion
	 */
	public String getTipoPrestacion() {
		return tipoPrestacion;
	}

	/**
	 * Gets the tipo trab aportacion cuota social.
	 *
	 * @return el atributo tipoTrabAportacionCuotaSocial
	 */
	public String getTipoTrabAportacionCuotaSocial() {
		return tipoTrabAportacionCuotaSocial;
	}

	/**
	 * Gets the tipo tramite.
	 *
	 * @return el atributo tipoTramite
	 */
	public String getTipoTramite() {
		return tipoTramite;
	}

	/**
	 * Gets the total resoluciones procesar.
	 *
	 * @return el atributo totalResolucionesProcesar
	 */
	public String getTotalResolucionesProcesar() {
		return totalResolucionesProcesar;
	}

	/**
	 * Gets the umf.
	 *
	 * @return el atributo umf
	 */
	public String getUmf() {
		return umf;
	}

	/**
	 * Sets the apellido materno.
	 *
	 * @param apellidoMaterno parametro apellidoMaterno a actualizar
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * Sets the apellido paterno.
	 *
	 * @param apellidoPaterno parametro apellidoPaterno a actualizar
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * Sets the clave admin actual.
	 *
	 * @param claveAdminActual parametro claveAdminActual a actualizar
	 */
	public void setClaveAdminActual(String claveAdminActual) {
		this.claveAdminActual = claveAdminActual;
	}

	/**
	 * Sets the curp.
	 *
	 * @param curp parametro curp a actualizar
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}

	/**
	 * Sets the curp agente servicio.
	 *
	 * @param curpAgenteServicio parametro curpAgenteServicio a actualizar
	 */
	public void setCurpAgenteServicio(String curpAgenteServicio) {
		this.curpAgenteServicio = curpAgenteServicio;
	}

	/**
	 * Sets the curp solicitante.
	 *
	 * @param curpSolicitante parametro curpSolicitante a actualizar
	 */
	public void setCurpSolicitante(String curpSolicitante) {
		this.curpSolicitante = curpSolicitante;
	}

	/**
	 * Sets the delegacion.
	 *
	 * @param delegacion parametro delegacion a actualizar
	 */
	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}

	/**
	 * Sets the diagnostico procesar.
	 *
	 * @param diagnosticoProcesar parametro diagnosticoProcesar a actualizar
	 */
	public void setDiagnosticoProcesar(String diagnosticoProcesar) {
		this.diagnosticoProcesar = diagnosticoProcesar;
	}

	/**
	 * Sets the entidad origen.
	 *
	 * @param entidadOrigen parametro entidadOrigen a actualizar
	 */
	public void setEntidadOrigen(String entidadOrigen) {
		this.entidadOrigen = entidadOrigen;
	}

	/**
	 * Sets the fecha baja trabajador.
	 *
	 * @param fechaBajaTrabajador parametro fechaBajaTrabajador a actualizar
	 */
	public void setFechaBajaTrabajador(String fechaBajaTrabajador) {
		this.fechaBajaTrabajador = fechaBajaTrabajador;
	}

	/**
	 * Sets the fecha conclusion vigencia.
	 *
	 * @param fechaConclusionVigencia parametro fechaConclusionVigencia a actualizar
	 */
	public void setFechaConclusionVigencia(String fechaConclusionVigencia) {
		this.fechaConclusionVigencia = fechaConclusionVigencia;
	}

	/**
	 * Sets the fecha matrimonio desempleo.
	 *
	 * @param fechaMatrimonioDesempleo parametro fechaMatrimonioDesempleo a
	 *                                 actualizar
	 */
	public void setFechaMatrimonioDesempleo(String fechaMatrimonioDesempleo) {
		this.fechaMatrimonioDesempleo = fechaMatrimonioDesempleo;
	}

	/**
	 * Sets the fecha notificacion imss.
	 *
	 * @param fechaNotificacionImss parametro fechaNotificacionImss a actualizar
	 */
	public void setFechaNotificacionImss(String fechaNotificacionImss) {
		this.fechaNotificacionImss = fechaNotificacionImss;
	}

	/**
	 * Sets the fecha prest tramite.
	 *
	 * @param fechaPrestTramite parametro fechaPrestTramite a actualizar
	 */
	public void setFechaPrestTramite(String fechaPrestTramite) {
		this.fechaPrestTramite = fechaPrestTramite;
	}

	/**
	 * Sets the folio operacion IMSS.
	 *
	 * @param folioOperacionIMSS parametro folioOperacionIMSS a actualizar
	 */
	public void setFolioOperacionIMSS(String folioOperacionIMSS) {
		this.folioOperacionIMSS = folioOperacionIMSS;
	}

	/**
	 * Sets the id pago complementario.
	 *
	 * @param idPagoComplementario parametro idPagoComplementario a actualizar
	 */
	public void setIdPagoComplementario(String idPagoComplementario) {
		this.idPagoComplementario = idPagoComplementario;
	}

	/**
	 * Sets the id solicitante.
	 *
	 * @param idSolicitante parametro idSolicitante a actualizar
	 */
	public void setIdSolicitante(String idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	/**
	 * Sets the monto pagado retiro original.
	 *
	 * @param montoPagadoRetiroOriginal parametro montoPagadoRetiroOriginal a
	 *                                  actualizar
	 */
	public void setMontoPagadoRetiroOriginal(String montoPagadoRetiroOriginal) {
		this.montoPagadoRetiroOriginal = montoPagadoRetiroOriginal;
	}

	/**
	 * Sets the nombre trabajador.
	 *
	 * @param nombreTrabajador parametro nombreTrabajador a actualizar
	 */
	public void setNombreTrabajador(String nombreTrabajador) {
		this.nombreTrabajador = nombreTrabajador;
	}

	/**
	 * Sets the nombre trabajador imss.
	 *
	 * @param nombreTrabajadorImss parametro nombreTrabajadorImss a actualizar
	 */
	public void setNombreTrabajadorImss(String nombreTrabajadorImss) {
		this.nombreTrabajadorImss = nombreTrabajadorImss;
	}

	/**
	 * Sets the nombre trabajador procanase.
	 *
	 * @param nombreTrabajadorProcanase parametro nombreTrabajadorProcanase a
	 *                                  actualizar
	 */
	public void setNombreTrabajadorProcanase(String nombreTrabajadorProcanase) {
		this.nombreTrabajadorProcanase = nombreTrabajadorProcanase;
	}

	/**
	 * Sets the nss.
	 *
	 * @param nss parametro nss a actualizar
	 */
	public void setNss(String nss) {
		this.nss = nss;
	}

	/**
	 * Sets the numero consecutivo procesar.
	 *
	 * @param numeroConsecutivoProcesar parametro numeroConsecutivoProcesar a
	 *                                  actualizar
	 */
	public void setNumeroConsecutivoProcesar(String numeroConsecutivoProcesar) {
		this.numeroConsecutivoProcesar = numeroConsecutivoProcesar;
	}

	/**
	 * Sets the numero resolucion.
	 *
	 * @param numeroResolucion parametro numeroResolucion a actualizar
	 */
	public void setNumeroResolucion(String numeroResolucion) {
		this.numeroResolucion = numeroResolucion;
	}

	/**
	 * Sets the origen.
	 *
	 * @param origen parametro origen a actualizar
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * Sets the resultado operacion.
	 *
	 * @param resultadoOperacion parametro resultadoOperacion a actualizar
	 */
	public void setResultadoOperacion(String resultadoOperacion) {
		this.resultadoOperacion = resultadoOperacion;
	}

	/**
	 * Sets the saldo anterior pago retiro original.
	 *
	 * @param saldoAnteriorPagoRetiroOriginal parametro
	 *                                        saldoAnteriorPagoRetiroOriginal a
	 *                                        actualizar
	 */
	public void setSaldoAnteriorPagoRetiroOriginal(String saldoAnteriorPagoRetiroOriginal) {
		this.saldoAnteriorPagoRetiroOriginal = saldoAnteriorPagoRetiroOriginal;
	}

	/**
	 * Sets the sbc tipo A.
	 *
	 * @param sbcTipoA parametro sbcTipoA a actualizar
	 */
	public void setSbcTipoA(String sbcTipoA) {
		this.sbcTipoA = sbcTipoA;
	}

	/**
	 * Sets the sbc tipo B.
	 *
	 * @param sbcTipoB parametro sbcTipoB a actualizar
	 */
	public void setSbcTipoB(String sbcTipoB) {
		this.sbcTipoB = sbcTipoB;
	}

	/**
	 * Sets the sello trabajador.
	 *
	 * @param selloTrabajador parametro selloTrabajador a actualizar
	 */
	public void setSelloTrabajador(String selloTrabajador) {
		this.selloTrabajador = selloTrabajador;
	}

	/**
	 * Sets the subdelegacion.
	 *
	 * @param subdelegacion parametro subdelegacion a actualizar
	 */
	public void setSubdelegacion(String subdelegacion) {
		this.subdelegacion = subdelegacion;
	}

	/**
	 * Sets the tipo prestacion.
	 *
	 * @param tipoPrestacion parametro tipoPrestacion a actualizar
	 */
	public void setTipoPrestacion(String tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
	}

	/**
	 * Sets the tipo trab aportacion cuota social.
	 *
	 * @param tipoTrabAportacionCuotaSocial parametro tipoTrabAportacionCuotaSocial
	 *                                      a actualizar
	 */
	public void setTipoTrabAportacionCuotaSocial(String tipoTrabAportacionCuotaSocial) {
		this.tipoTrabAportacionCuotaSocial = tipoTrabAportacionCuotaSocial;
	}

	/**
	 * Sets the tipo tramite.
	 *
	 * @param tipoTramite the new tipo tramite
	 */
	public void setTipoTramite(String tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	/**
	 * Sets the total resoluciones procesar.
	 *
	 * @param totalResolucionesProcesar parametro totalResolucionesProcesar a
	 *                                  actualizar
	 */
	public void setTotalResolucionesProcesar(String totalResolucionesProcesar) {
		this.totalResolucionesProcesar = totalResolucionesProcesar;
	}

	/**
	 * Sets the umf.
	 *
	 * @param umf parametro umf a actualizar
	 */
	public void setUmf(String umf) {
		this.umf = umf;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidarSolicitudCertificacionAforeSalida [resultadoOperacion=");
		builder.append(resultadoOperacion);
		builder.append(", diagnosticoProcesar=");
		builder.append(diagnosticoProcesar);
		builder.append(", entidadOrigen=");
		builder.append(entidadOrigen);
		builder.append(", tipoTramite=");
		builder.append(tipoTramite);
		builder.append(", nss=");
		builder.append(nss);
		builder.append(", curp=");
		builder.append(curp);
		builder.append(", nombreTrabajadorImss=");
		builder.append(nombreTrabajadorImss);
		builder.append(", nombreTrabajadorProcanase=");
		builder.append(nombreTrabajadorProcanase);
		builder.append(", nombreTrabajador=");
		builder.append(nombreTrabajador);
		builder.append(", apellidoPaterno=");
		builder.append(apellidoPaterno);
		builder.append(", apellidoMaterno=");
		builder.append(apellidoMaterno);
		builder.append(", tipoPrestacion=");
		builder.append(tipoPrestacion);
		builder.append(", fechaMatrimonioDesempleo=");
		builder.append(fechaMatrimonioDesempleo);
		builder.append(", fechaNotificacionImss=");
		builder.append(fechaNotificacionImss);
		builder.append(", fechaConclusionVigencia=");
		builder.append(fechaConclusionVigencia);
		builder.append(", numeroResolucion=");
		builder.append(numeroResolucion);
		builder.append(", tipoTrabAportacionCuotaSocial=");
		builder.append(tipoTrabAportacionCuotaSocial);
		builder.append(", sbcTipoA=");
		builder.append(sbcTipoA);
		builder.append(", sbcTipoB=");
		builder.append(sbcTipoB);
		builder.append(", idPagoComplementario=");
		builder.append(idPagoComplementario);
		builder.append(", montoPagadoRetiroOriginal=");
		builder.append(montoPagadoRetiroOriginal);
		builder.append(", saldoAnteriorPagoRetiroOriginal=");
		builder.append(saldoAnteriorPagoRetiroOriginal);
		builder.append(", folioOperacionIMSS=");
		builder.append(folioOperacionIMSS);
		builder.append(", delegacion=");
		builder.append(delegacion);
		builder.append(", subdelegacion=");
		builder.append(subdelegacion);
		builder.append(", umf=");
		builder.append(umf);
		builder.append(", fechaPrestTramite=");
		builder.append(fechaPrestTramite);
		builder.append(", fechaBajaTrabajador=");
		builder.append(fechaBajaTrabajador);
		builder.append(", numeroConsecutivoProcesar=");
		builder.append(numeroConsecutivoProcesar);
		builder.append(", totalResolucionesProcesar=");
		builder.append(totalResolucionesProcesar);
		builder.append(", claveAdminActual=");
		builder.append(claveAdminActual);
		builder.append(", origen=");
		builder.append(origen);
		builder.append(", idSolicitante=");
		builder.append(idSolicitante);
		builder.append(", curpSolicitante=");
		builder.append(curpSolicitante);
		builder.append(", selloTrabajador=");
		builder.append(selloTrabajador);
		builder.append(", curpAgenteServicio=");
		builder.append(curpAgenteServicio);
		builder.append("]");
		return builder.toString();
	}
}
