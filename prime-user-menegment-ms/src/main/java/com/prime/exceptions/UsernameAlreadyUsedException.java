package com.prime.exceptions;

import com.prime.common.exception.InvalidStateException;

public class UsernameAlreadyUsedException extends InvalidStateException {

    private static final long serialVersionUID = 1L;

    public UsernameAlreadyUsedException(String username) {
        super(String.format("Username \"%s\" already exist,try different username", username));
    }
}
