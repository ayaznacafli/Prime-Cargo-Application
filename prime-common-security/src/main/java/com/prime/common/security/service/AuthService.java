package com.prime.common.security.service;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {

    Optional<Authentication> getAuthentication(HttpServletRequest httpServletRequest);

}
