package com.prime.common.security.config;

import com.prime.common.security.filters.AuthRequestFilter;
import com.prime.common.security.service.AuthService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
public class AuthFilterConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final List<AuthService> authServices;

    @Override
    public void configure(HttpSecurity http) {
        log.trace("Added auth request filter");
        http.addFilterBefore(new AuthRequestFilter(authServices), UsernamePasswordAuthenticationFilter.class);
    }
}
