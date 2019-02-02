package com.starknetworks.crosscutting.exceptions;

public class BusinessException extends RuntimeException {

    /**
     * Constructs a new Business Exception
     *
     * @param message - describing the error that occurred
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructs a new Business Exception
     *
     * @param message - describing the error that occurred
     * @param cause   - cause of the error
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
