package com.prime.exceptions;

import com.prime.common.security.model.ERole;

public class AuthorityNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -3042686055658047285L;

    public AuthorityNotFoundException() {
        super("Authority not found.");
    }

    public AuthorityNotFoundException(ERole name) {
        super(String.format("Authority with name %d not found.", name.name()));
    }

}
