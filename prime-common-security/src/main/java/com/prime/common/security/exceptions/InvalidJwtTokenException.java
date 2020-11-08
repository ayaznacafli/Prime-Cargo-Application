package com.prime.common.security.exceptions;

public class InvalidJwtTokenException extends UnauthorizedException {

    private static final long serialVersionUID = 5894896710747318006L;
    private static final String MESSAGE = "The JWT token is invalid";

    public InvalidJwtTokenException(RuntimeException exception) {
        super(MESSAGE, exception);
    }
}
