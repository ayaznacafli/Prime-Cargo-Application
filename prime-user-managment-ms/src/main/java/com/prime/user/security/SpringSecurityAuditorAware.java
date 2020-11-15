package com.prime.user.security;

import com.prime.common.security.service.SecurityService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    private final SecurityService securityService;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(securityService.getCurrentUserLogin().orElse("system"));
    }
}
