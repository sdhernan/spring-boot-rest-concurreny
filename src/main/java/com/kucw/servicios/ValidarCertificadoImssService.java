package com.kucw.servicios;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kucw.presentacion.modelos.ValidarSolicitudCertificacionAforeSalida;

/**
 * The Class ValidarCertificadoImssService.
 */
@Service
public class ValidarCertificadoImssService {

	/**
	 * Validar informacion.
	 *
	 * @param entrada the entrada
	 */
	@Transactional
	public void validarInformacion(ValidarSolicitudCertificacionAforeSalida entrada) {

	}

}
