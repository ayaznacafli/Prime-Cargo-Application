package com.prime.common.exception;

public class InvalidApplicationStepException extends InvalidStateException {

    private static final long serialVersionUID = 58432132465811L;

    public InvalidApplicationStepException(String message) {
        super(message);
    }
}
