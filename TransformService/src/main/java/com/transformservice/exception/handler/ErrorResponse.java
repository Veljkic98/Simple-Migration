package com.transformservice.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transformservice.util.ApplicationConstants;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ApplicationConstants.DATE_PATTERN)
    private final Date date;

    @JsonIgnore
    private final HttpStatus httpStatus;

    private final int status;

    private final String error;

    private final String message;

    private final String path;

    public ErrorResponse(HttpServletRequest request, String message, HttpStatus httpStatus) {
        this.date = new Date();
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = extractPath(request);
    }

    // get

    public Date getDate() {
        return date;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    private String extractPath(HttpServletRequest request) {
        return ObjectUtils.firstNonNull(
                Objects.toString(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE), null),
                request.getServletPath()
        );
    }

}
