package com.prime.service;

import com.prime.service.payload.request.LoginRequest;
import com.prime.service.payload.request.SigninRequest;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity<?> createUser(SigninRequest request);

    ResponseEntity<?> loginUser(LoginRequest request);

}
