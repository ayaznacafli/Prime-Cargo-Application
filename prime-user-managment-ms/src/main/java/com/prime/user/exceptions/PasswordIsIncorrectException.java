package com.prime.user.exceptions;

import com.prime.common.exception.InvalidStateException;

public class PasswordIsIncorrectException extends InvalidStateException {

    public static final String OLD_PASSWORD_NOT_CORRECT = "Old password is not correct";

    private static final long serialVersionUID = 39453245432534L;

    public PasswordIsIncorrectException() {
        super(OLD_PASSWORD_NOT_CORRECT);
    }
}
