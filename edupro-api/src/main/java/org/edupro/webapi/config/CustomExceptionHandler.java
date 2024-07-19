package org.edupro.webapi.config;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.exception.EmptyResponseBodyException;
import org.edupro.webapi.base.model.ResponseError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.sql.SQLException;
import java.util.*;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ResponseError> handleException(HttpRequestMethodNotSupportedException e) {
        log.info("Exception occurred ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.UNSUPPORTED_METHOD)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ResponseError> handleException(AccessDeniedException e) {
        log.info("Forbidden access ", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseError.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message(MessageApp.FORBIDDEN)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseError> handleException(HttpMessageNotReadableException e) {
        log.info("Unreadable Input", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.UNREADABLE_INPUT)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ResponseError> handleException(ConstraintViolationException e) {
        log.info("Constraint Violated ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {SQLException.class})
    public ResponseEntity<ResponseError> handleException(SQLException e) {
        log.info("SQLException ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseError> handleException(MethodArgumentNotValidException e) {
        List<Map<String, Object>> errors = new ArrayList<>();
        for(FieldError error: e.getBindingResult().getFieldErrors()){
            errors.add(Map.of(error.getField(), error.getDefaultMessage()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(errors)
                .build());
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ResponseError> handleException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity<ResponseError> handleException(MissingServletRequestParameterException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_PARAMS)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<ResponseError> handleException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ResponseError> handleException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public ResponseEntity<ResponseError> handleException(MissingServletRequestPartException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {MissingRequestHeaderException.class})
    public ResponseEntity<ResponseError> handleException(MissingRequestHeaderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ResponseError> handleException(HttpMediaTypeNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(MessageApp.UNSUPPORTED_MEDIA_TYPE)
                .build());
    }

    @ExceptionHandler(value = {MultipartException.class})
    public ResponseEntity<ResponseError> handleException(MultipartException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors("The server encountered an issue while processing the request.")
                .build());
    }

    @ExceptionHandler(value = {EmptyResponseBodyException.class})
    public ResponseEntity<ResponseError> handleException(EmptyResponseBodyException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ResponseError.builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<ResponseError> handleException(BindException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(MessageApp.INVALID_INPUT)
                .errors(errors)
                .build());
    }

    @ExceptionHandler(value = {EduProApiException.class})
    public ResponseEntity<ResponseError> handleException(EduProApiException e) {
        if(e.getErrors() == null) {
            return ResponseEntity.status(e.getStatus()).body(ResponseError.builder()
                    .statusCode(e.getStatus().value())
                    .message(e.getMessage())
                    .build());
        }

        return ResponseEntity.status(e.getStatus()).body(ResponseError.builder()
                .statusCode(e.getStatus().value())
                .message(e.getMessage())
                .errors(e.getErrors())
                .build());
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ResponseError> handleException(AuthenticationException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseError.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message(MessageApp.INVALID_AUTH)
                .errors(e.getMessage())
                .build());
    }
}