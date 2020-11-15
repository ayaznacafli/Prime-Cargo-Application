package com.prime.user.service;

import com.prime.user.service.payload.request.LoginRequest;
import com.prime.user.service.payload.request.SigninRequest;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity<?> createUser(SigninRequest request);

    ResponseEntity<?> loginUser(LoginRequest request);

}
