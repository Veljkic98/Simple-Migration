package com.transformservice.exception;

public class DataMissingException extends RuntimeException {

    public DataMissingException() {
        super();
    }

    public DataMissingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DataMissingException(final String message) {
        super(message);
    }

    public DataMissingException(final Throwable cause) {
        super(cause);
    }

}
