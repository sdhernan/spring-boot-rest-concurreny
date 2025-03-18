package com.kucw.presentacion.excepciones;

/**
 * The Class ConcurrencyException.
 */
public class ConcurrencyException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new concurrency exception.
	 *
	 * @param message the message
	 */
	public ConcurrencyException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new concurrency exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public ConcurrencyException(String message, Throwable cause) {
		super(message, cause);
	}
}
