package com.kucw.presentacion.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * The Class ValidarSolicitudCertificacionAforeEntrada.
 *
 * @author jmcabrer
 */
public class ValidarSolicitudCertificacionAforeEntrada implements Serializable {

	/** Serial id. */
	private static final long serialVersionUID = 792016707836233848L;

	/** folioDeTramiteProcesar. */
	private String folioTramiteProcesar;

	/** nss. */
	private String nss;

	/** curp. */
	private String curp;

	/** nombreTrabajador. */
	private String nombreTrabajador;

	/** apellidoPaterno. */
	private String apellidoPaterno;

	/** apellidoMaterno. */
	private String apellidoMaterno;

	/** tipoDePrestacion. */
	private String tipoPrestacion;

	/** claveAdminActual. */
	private String claveAdminActual;

	/** origen. */
	private BigInteger origen;

	/** idSolicitante. */
	private String idSolicitante;

	/** curpSolicitante. */
	private String curpSolicitante;

	/** selloTrabajador. */
	private Long selloTrabajador;

	/** curpAgenteServicio. */
	private String curpAgenteServicio;

	/** Nombre Trabajador Imss. */
	private String nombreTrabajadorImss;

	/** Nombre Trabajador Procanase. */
	private String nombreTrabProcanase;

	/** Estatus de la operacion 20600. */
	private String estatus20600;

	/** Estatus de la operacion 20700. */
	private String estatus20700;

	/** Estatus general. */
	private Long estatus;

	/** SeConsultaCertificadoImss. */
	private boolean consultaCertificadoImss;

	/** nuevoRechazoJ62. */
	private boolean nuevoRechazoJ62;

	/** clave de la operacion. */
	private String operacion;

	/** folio Operacion IMSS. */
	private String folioOperacionIMSS;

	/** indicadorOrigenTramite. */
	private String indicadorOrigenTramite;

	/** fecha Conclusion Vigencia. */
	private String fechaConclusionVigencia;

	/** Existe certificado. */
	private Boolean existeCertificado;

	/** fecha Fin Vigencia. */
	private String fechaFinVigencia;

	/** The diagnostico certificado encontrado. */
	private String diagnosticoCertificadoEncontrado;

	/** The diagnostico original. */
	private String diagnosticoOriginal;

	/** The diagnostico inicial. */
	private String diagnosticoInicial;

	/** The ultimo sario imss. */
	private BigDecimal ultimoSarioImss;

	/**
	 * Instantiates a new validar solicitud certificacion afore entrada.
	 */
	public ValidarSolicitudCertificacionAforeEntrada() {
		super();
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public String getClaveAdminActual() {
		return claveAdminActual;
	}

	public String getCurp() {
		return curp;
	}

	public String getCurpAgenteServicio() {
		return curpAgenteServicio;
	}

	public String getCurpSolicitante() {
		return curpSolicitante;
	}

	public String getDiagnosticoCertificadoEncontrado() {
		return diagnosticoCertificadoEncontrado;
	}

	public String getDiagnosticoInicial() {
		return diagnosticoInicial;
	}

	public String getDiagnosticoOriginal() {
		return diagnosticoOriginal;
	}

	public Long getEstatus() {
		return estatus;
	}

	public String getEstatus20600() {
		return estatus20600;
	}

	public String getEstatus20700() {
		return estatus20700;
	}

	public Boolean getExisteCertificado() {
		return existeCertificado;
	}

	public String getFechaConclusionVigencia() {
		return fechaConclusionVigencia;
	}

	public String getFechaFinVigencia() {
		return fechaFinVigencia;
	}

	public String getFolioOperacionIMSS() {
		return folioOperacionIMSS;
	}

	public String getFolioTramiteProcesar() {
		return folioTramiteProcesar;
	}

	public String getIdSolicitante() {
		return idSolicitante;
	}

	public String getIndicadorOrigenTramite() {
		return indicadorOrigenTramite;
	}

	public String getNombreTrabajador() {
		return nombreTrabajador;
	}

	public String getNombreTrabajadorImss() {
		return nombreTrabajadorImss;
	}

	public String getNombreTrabProcanase() {
		return nombreTrabProcanase;
	}

	public String getNss() {
		return nss;
	}

	public String getOperacion() {
		return operacion;
	}

	public BigInteger getOrigen() {
		return origen;
	}

	public Long getSelloTrabajador() {
		return selloTrabajador;
	}

	public String getTipoPrestacion() {
		return tipoPrestacion;
	}

	public BigDecimal getUltimoSarioImss() {
		return ultimoSarioImss;
	}

	public boolean isConsultaCertificadoImss() {
		return consultaCertificadoImss;
	}

	public boolean isNuevoRechazoJ62() {
		return nuevoRechazoJ62;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public void setClaveAdminActual(String claveAdminActual) {
		this.claveAdminActual = claveAdminActual;
	}

	public void setConsultaCertificadoImss(boolean consultaCertificadoImss) {
		this.consultaCertificadoImss = consultaCertificadoImss;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public void setCurpAgenteServicio(String curpAgenteServicio) {
		this.curpAgenteServicio = curpAgenteServicio;
	}

	public void setCurpSolicitante(String curpSolicitante) {
		this.curpSolicitante = curpSolicitante;
	}

	public void setDiagnosticoCertificadoEncontrado(String diagnosticoCertificadoEncontrado) {
		this.diagnosticoCertificadoEncontrado = diagnosticoCertificadoEncontrado;
	}

	public void setDiagnosticoInicial(String diagnosticoInicial) {
		this.diagnosticoInicial = diagnosticoInicial;
	}

	public void setDiagnosticoOriginal(String diagnosticoOriginal) {
		this.diagnosticoOriginal = diagnosticoOriginal;
	}

	public void setEstatus(Long estatus) {
		this.estatus = estatus;
	}

	public void setEstatus20600(String estatus20600) {
		this.estatus20600 = estatus20600;
	}

	public void setEstatus20700(String estatus20700) {
		this.estatus20700 = estatus20700;
	}

	public void setExisteCertificado(Boolean existeCertificado) {
		this.existeCertificado = existeCertificado;
	}

	public void setFechaConclusionVigencia(String fechaConclusionVigencia) {
		this.fechaConclusionVigencia = fechaConclusionVigencia;
	}

	public void setFechaFinVigencia(String fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}

	public void setFolioOperacionIMSS(String folioOperacionIMSS) {
		this.folioOperacionIMSS = folioOperacionIMSS;
	}

	public void setFolioTramiteProcesar(String folioTramiteProcesar) {
		this.folioTramiteProcesar = folioTramiteProcesar;
	}

	public void setIdSolicitante(String idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public void setIndicadorOrigenTramite(String indicadorOrigenTramite) {
		this.indicadorOrigenTramite = indicadorOrigenTramite;
	}

	public void setNombreTrabajador(String nombreTrabajador) {
		this.nombreTrabajador = nombreTrabajador;
	}

	public void setNombreTrabajadorImss(String nombreTrabajadorImss) {
		this.nombreTrabajadorImss = nombreTrabajadorImss;
	}

	public void setNombreTrabProcanase(String nombreTrabProcanase) {
		this.nombreTrabProcanase = nombreTrabProcanase;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public void setNuevoRechazoJ62(boolean nuevoRechazoJ62) {
		this.nuevoRechazoJ62 = nuevoRechazoJ62;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public void setOrigen(BigInteger origen) {
		this.origen = origen;
	}

	public void setSelloTrabajador(Long selloTrabajador) {
		this.selloTrabajador = selloTrabajador;
	}

	public void setTipoPrestacion(String tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
	}

	public void setUltimoSarioImss(BigDecimal ultimoSarioImss) {
		this.ultimoSarioImss = ultimoSarioImss;
	}

}
