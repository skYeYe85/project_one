package com.exception;

public class ExoplanetPersistenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExoplanetPersistenceException() {
		super();
	}

	public ExoplanetPersistenceException(String message) {
		super(message);
	}

	public ExoplanetPersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExoplanetPersistenceException(Throwable cause) {
		super(cause);
	}

	protected ExoplanetPersistenceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
