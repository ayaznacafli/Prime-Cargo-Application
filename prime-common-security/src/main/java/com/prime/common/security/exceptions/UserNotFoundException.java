package com.prime.common.security.exceptions;


import com.prime.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public static final String USER_NOT_FOUND = "User Not Found";

    private static final long serialVersionUID = 58432132465811L;

    public UserNotFoundException(String username) {
        super(String.format("User \"%s\" not found", username));
    }

    public UserNotFoundException(String param, String email) {
        super(String.format("User with \"%s\" - \"%s\" not found", param, email));
    }

    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
