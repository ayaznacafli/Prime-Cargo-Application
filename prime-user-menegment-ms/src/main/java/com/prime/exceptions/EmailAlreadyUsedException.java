package com.prime.exceptions;

import com.prime.common.exception.InvalidStateException;

public class EmailAlreadyUsedException extends InvalidStateException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException(String email) {
        super(String.format("Email \"%s\" already exist,try different username", email));
    }
}
