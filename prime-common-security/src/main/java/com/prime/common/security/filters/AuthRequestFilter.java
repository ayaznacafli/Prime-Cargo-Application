package com.prime.common.security.filters;

import com.prime.common.security.service.AuthService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class AuthRequestFilter extends OncePerRequestFilter {

    private final List<AuthService> authServices;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws IOException {
        log.trace("Filtering request against auth services {}", authServices);
        try {
            Optional<Authentication> authOptional = Optional.empty();
            for (AuthService authService : authServices) {
                authOptional = authOptional.or(() -> authService.getAuthentication(httpServletRequest));
            }
            authOptional.ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (@SuppressWarnings("PMD.AvoidCatchingGenericException") Exception exception) {
            log.trace("Exception during authentication",exception);
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        }
    }
}
