package com.prime.service;

import com.prime.service.payload.request.PasswordRequest;
import org.springframework.http.ResponseEntity;

public interface PasswordService {
    ResponseEntity<?> changePassword(PasswordRequest request);
}
