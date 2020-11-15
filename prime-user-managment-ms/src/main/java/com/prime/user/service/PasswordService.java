package com.prime.user.service;

import com.prime.user.service.payload.request.PasswordRequest;
import org.springframework.http.ResponseEntity;

public interface PasswordService {
    ResponseEntity<?> changePassword(PasswordRequest request);
}
