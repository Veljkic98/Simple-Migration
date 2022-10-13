package com.transformservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ReferentialIntegrityConstraintViolationException extends RuntimeException {

    public ReferentialIntegrityConstraintViolationException() {
        super();
    }

    public ReferentialIntegrityConstraintViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ReferentialIntegrityConstraintViolationException(final String message) {
        super(message);
    }

    public ReferentialIntegrityConstraintViolationException(final Throwable cause) {
        super(cause);
    }

}
