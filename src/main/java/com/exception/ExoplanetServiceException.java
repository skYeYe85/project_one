package com.exception;

public class ExoplanetServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExoplanetServiceException() {
		super();
	}

	public ExoplanetServiceException(String message) {
		super(message);
	}

	public ExoplanetServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExoplanetServiceException(Throwable cause) {
		super(cause);
	}

	protected ExoplanetServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
