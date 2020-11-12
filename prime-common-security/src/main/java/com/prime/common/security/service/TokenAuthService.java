package com.prime.common.security.service;

import com.prime.common.HttpConstants;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public final class TokenAuthService implements AuthService {

    private static final String ROLE_CLAIM = "rule";
    private final JwtService jwtService;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest req) {
        return Optional.ofNullable(req.getHeader(HttpConstants.AUTH_HEADER))
                .filter(this::isBearerAuth)
                .flatMap(this::getAuthenticationBearer);
    }

    private boolean isBearerAuth(String header) {
        return header.toLowerCase().startsWith(HttpConstants.BEARER_AUTH_HEADER.toLowerCase());
    }

    private Optional<Authentication> getAuthenticationBearer(String header) {
        String token = header.substring(HttpConstants.BEARER_AUTH_HEADER.length()).trim();
        Claims claims = jwtService.parseToken(token);
        log.trace("The claims parsed {}", claims);
        if (claims.getExpiration().before(new Date())) {
            return Optional.empty();
        }
        return Optional.of(getAuthenticationBearer(claims));
    }

    private Authentication getAuthenticationBearer(Claims claims) {
        List<?> roles = claims.get(ROLE_CLAIM, List.class);
        List<GrantedAuthority> authorityList = roles
                .stream()
                .map(a -> new SimpleGrantedAuthority(a.toString()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorityList);
    }

}
