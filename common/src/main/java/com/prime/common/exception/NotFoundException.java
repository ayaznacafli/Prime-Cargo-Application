package com.prime.common.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 58432132465811L;

    public NotFoundException(String message) {
        super(message);
    }
}
