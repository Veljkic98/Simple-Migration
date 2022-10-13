package com.transformservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
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
