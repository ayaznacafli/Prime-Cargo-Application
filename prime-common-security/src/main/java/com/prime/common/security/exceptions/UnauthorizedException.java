package com.prime.common.security.exceptions;

public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 5853558719338611079L;

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
