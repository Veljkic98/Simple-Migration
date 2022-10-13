package com.transformservice.exception.handler;

import com.opencsv.exceptions.CsvValidationException;
import com.transformservice.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    private final HttpServletRequest request;

    public RestResponseEntityExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    // 400

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    protected ResponseEntity<ErrorResponse> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        final String message = "Data Integrity Exception occurred.";
        return createResponseEntity(createExceptionWrapper(message, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = { IOException.class, CsvValidationException.class })
    protected ResponseEntity<ErrorResponse> handleBadRequest() {
        final String bodyOfResponse = "Incorrect data in file.";
        return createResponseEntity(createExceptionWrapper(bodyOfResponse, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = { ReferentialIntegrityConstraintViolationException.class})
    protected ResponseEntity<ErrorResponse> handleBadRequest(final ReferentialIntegrityConstraintViolationException ex) {
        return createResponseEntity(createExceptionWrapper(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = { DataMissingException.class })
    protected ResponseEntity<ErrorResponse> handleBadRequest(final DataMissingException ex, final WebRequest request) {
        return createResponseEntity(createExceptionWrapper(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = { InvalidDataException.class })
    protected ResponseEntity<ErrorResponse> handleBadRequest(final InvalidDataException ex) {
        return createResponseEntity(createExceptionWrapper(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    // 404

    @ExceptionHandler(value = { DataNotFoundException.class })
    protected ResponseEntity<ErrorResponse> handleNotFound(final DataNotFoundException ex) {
        return createResponseEntity(createExceptionWrapper(ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(value = { ColumnDataMissingException.class })
    //todo: delete this zato sto ne koristimo ovaj exception
    protected ResponseEntity<ErrorResponse> handleNotFound(final ColumnDataMissingException ex, final WebRequest request) {
        return createResponseEntity(createExceptionWrapper(ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    private ErrorResponse createExceptionWrapper(String message, HttpStatus httpStatus) {
        return new ErrorResponse(request, message, httpStatus);
    }

}
