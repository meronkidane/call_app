package com.streamhub.common.exception;

public class BadRequestException extends DomainException {
    public BadRequestException(String code, String message) {
        super(code, message);
    }

    public BadRequestException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
