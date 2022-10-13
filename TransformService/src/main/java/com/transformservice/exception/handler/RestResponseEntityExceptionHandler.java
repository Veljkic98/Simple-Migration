package com.transformservice.exception.handler;

import com.opencsv.exceptions.CsvValidationException;
import com.transformservice.domain.dto.ErrorResponseDto;
import com.transformservice.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    protected ResponseEntity<ErrorResponseDto> handleBadRequest() {
        final String bodyOfResponse = "Incorrect data in file.";
        return createResponseEntity(createExceptionWrapper(bodyOfResponse, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = { ReferentialIntegrityConstraintViolationException.class})
    protected ResponseEntity<ErrorResponseDto> handleBadRequest(final ReferentialIntegrityConstraintViolationException e) {
        log.warn(e.getMessage());
        return createResponseEntity(createExceptionWrapper(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = { DataMissingException.class })
    protected ResponseEntity<ErrorResponseDto> handleBadRequest(final DataMissingException e) {
        log.warn(e.getMessage());
        return createResponseEntity(createExceptionWrapper(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = { InvalidDataException.class })
    protected ResponseEntity<ErrorResponseDto> handleBadRequest(final InvalidDataException e) {
        log.warn(e.getMessage());
        return createResponseEntity(createExceptionWrapper(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    // 404

    @ExceptionHandler(value = { DataNotFoundException.class })
    protected ResponseEntity<ErrorResponseDto> handleNotFound(final DataNotFoundException e) {
        log.warn(e.getMessage());
        return createResponseEntity(createExceptionWrapper(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<ErrorResponseDto> createResponseEntity(ErrorResponseDto errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    private ErrorResponseDto createExceptionWrapper(String message, HttpStatus httpStatus) {
        return new ErrorResponseDto(request, message, httpStatus);
    }

}
