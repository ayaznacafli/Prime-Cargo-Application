package com.prime.common.security.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationEntryPointConfigurer implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException exception) throws IOException {
        log.error("Responding with unauthorized error: {}", exception.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
    }

}
