package com.prime.common.security.exceptions;

public class NoTokenPresentException extends UnauthorizedException {

    private static final long serialVersionUID = -1495322745973501341L;
    private static final String MESSAGE = "Authorization (jwt) token not present";

    public NoTokenPresentException() {
        super(MESSAGE);
    }
}
