package com.transformservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
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
