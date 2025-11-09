package com.streamhub.common.exception;

public class ForbiddenException extends DomainException {
    public ForbiddenException(String code, String message) {
        super(code, message);
    }
}
