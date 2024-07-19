package org.edupro.webapi.exception;

public class JsonParseException extends RuntimeException {
    public JsonParseException(String message, Throwable cause) {
        super(message, cause);
    }
}