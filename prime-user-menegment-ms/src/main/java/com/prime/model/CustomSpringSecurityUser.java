package com.prime.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomSpringSecurityUser extends User {

    private static final long serialVersionUID = 3522416053866116034L;

    public CustomSpringSecurityUser(String username, String password,
                                    Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    
}
