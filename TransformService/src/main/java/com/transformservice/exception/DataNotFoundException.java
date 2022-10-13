package com.transformservice.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(final String message) {
        super(message);
    }

    public DataNotFoundException(final Throwable cause) {
        super(cause);
    }

}
