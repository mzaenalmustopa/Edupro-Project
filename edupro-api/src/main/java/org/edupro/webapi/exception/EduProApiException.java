package org.edupro.webapi.exception;

import org.springframework.http.HttpStatus;

public class EduProApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    private final Object errors;

    public EduProApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = null;
    }

    public EduProApiException(String message, HttpStatus status, Object errors) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getErrors() {
        return errors;
    }
}
