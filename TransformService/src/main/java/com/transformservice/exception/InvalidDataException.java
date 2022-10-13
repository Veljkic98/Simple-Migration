package com.transformservice.exception;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException() {
        super();
    }

    public InvalidDataException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidDataException(final String message) {
        super(message);
    }

    public InvalidDataException(final Throwable cause) {
        super(cause);
    }

}
