package com.transformservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ColumnDataMissingException extends RuntimeException {

    public ColumnDataMissingException() {
        super();
    }

    public ColumnDataMissingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ColumnDataMissingException(final String message) {
        super(message);
    }

    public ColumnDataMissingException(final Throwable cause) {
        super(cause);
    }

}
