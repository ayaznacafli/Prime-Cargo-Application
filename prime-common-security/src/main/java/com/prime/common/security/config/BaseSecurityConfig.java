package com.prime.common.security.config;

import com.prime.common.security.model.ERole;
import com.prime.common.security.service.AuthService;
import java.util.List;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;


@RequiredArgsConstructor
public class BaseSecurityConfig extends WebSecurityConfigurerAdapter {

    private final List<AuthService> authServices;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        //Disallow all requests by default unless explicitly defined in submodules
        http.authorizeRequests().anyRequest().access(authorities(ERole.ADMIN.name()));
        // Apply AuthRequestFilter
        http.apply(new AuthFilterConfigurerAdapter(authServices));
    }

    protected String authority(String role) {
        return "hasAuthority('" + role + "')";
    }

    protected String authority(ERole role) {
        return "hasAuthority('" + role.name() + "')";
    }

    protected String authorities(Object... roles) {
        StringJoiner joiner = new StringJoiner(" or ");
        for (Object role : roles) {
            if (role instanceof ERole) {
                joiner.add(authority((ERole) role));
            } else {
                joiner.add(authority(role.toString()));
            }
        }
        return joiner.toString();
    }
}
