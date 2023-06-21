package com.unexus.minegocio.exception;

import java.util.List;

/**
 * Manager exceptions.
 * @author Esteban G.
 *
 */
public class MiNegocioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> subErrors;
	/**
     * Constructor.
     */
    public MiNegocioException() {
        super();
    }

    /**
     * Constructor with args.
     *
     * @param message            The message
     * @param cause              The cause
     * @param enableSuppression  true if want to enable suppression, false if not.
     * @param writableStackTrace true if are writable the stack trace, false if not.
     */
    public MiNegocioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message,
              cause,
              enableSuppression,
              writableStackTrace);
    }

    /**
     * Constructor with args.
     *
     * @param message The message
     * @param cause   The cause
     */
    public MiNegocioException(String message, Throwable cause) {
        super(message,
              cause);
    }

    /**
     * Constructor with args. Personalize exception for ADJ.
     *
     * @param msg A message for exception
     * @param subErrors a list with details of error
     */
    public MiNegocioException(String msg, List<String> subErrors) {
        super(msg);
        this.subErrors = subErrors;
    }
    
    /**
     * Constructor with args. Personalize exception for ADJ.
     *
     * @param msg A message for exception
     */
    public MiNegocioException(String msg) {
        super(msg);
    }


    /**
     * Constructor with args.
     *
     * @param cause The cause
     */
    public MiNegocioException(Throwable cause) {
        super(cause);
    }

    
	public List<String> getSubErrors() {
		return subErrors;
	}
    
    
}
