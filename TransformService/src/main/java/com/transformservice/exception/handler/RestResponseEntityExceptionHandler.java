package com.transformservice.exception.handler;

import com.opencsv.exceptions.CsvValidationException;
import com.transformservice.exception.*;
import com.transformservice.service.impl.ReadingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    public RestResponseEntityExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    // 400

    //todo: delete this, sve treba da bude pokriveno...
//    @ExceptionHandler(value = { DataIntegrityViolationException.class })
//    protected ResponseEntity<ErrorResponse> handleBadRequest(final DataIntegrityViolationException ex) {
//        final String message = "Data Integrity Exception occurred.";
//        return createResponseEntity(createExceptionWrapper(message, HttpStatus.BAD_REQUEST));
//    }

    @ExceptionHandler(value = { IOException.class, CsvValidationException.class })
    protected ResponseEntity<ErrorResponse> handleBadRequest() {
        final String bodyOfResponse = "Incorrect data in file.";
        return createResponseEntity(createExceptionWrapper(bodyOfResponse, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = { ReferentialIntegrityConstraintViolationException.class})
    protected ResponseEntity<ErrorResponse> handleBadRequest(final ReferentialIntegrityConstraintViolationException e) {
        log.warn(e.getMessage());
        return createResponseEntity(createExceptionWrapper(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = { DataMissingException.class })
    protected ResponseEntity<ErrorResponse> handleBadRequest(final DataMissingException e) {
        log.warn(e.getMessage());
        return createResponseEntity(createExceptionWrapper(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = { InvalidDataException.class })
    protected ResponseEntity<ErrorResponse> handleBadRequest(final InvalidDataException e) {
        log.warn(e.getMessage());
        return createResponseEntity(createExceptionWrapper(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    // 404

    @ExceptionHandler(value = { DataNotFoundException.class })
    protected ResponseEntity<ErrorResponse> handleNotFound(final DataNotFoundException e) {
        log.warn(e.getMessage());
        return createResponseEntity(createExceptionWrapper(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(value = { ColumnDataMissingException.class })
    //todo: delete this zato sto ne koristimo ovaj exception
    protected ResponseEntity<ErrorResponse> handleNotFound(final ColumnDataMissingException e) {
        log.warn(e.getMessage());
        return createResponseEntity(createExceptionWrapper(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    private ErrorResponse createExceptionWrapper(String message, HttpStatus httpStatus) {
        return new ErrorResponse(request, message, httpStatus);
    }

}
