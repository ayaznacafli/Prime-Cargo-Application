package com.prime.common.security.service;

import com.prime.common.security.model.Claim;
import org.springframework.security.core.Authentication;

public interface ClaimProvider {

    Claim provide(Authentication authentication);

}
