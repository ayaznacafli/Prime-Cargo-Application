package com.prime.controller;

import com.prime.service.PasswordService;
import com.prime.service.UserService;
import com.prime.service.payload.request.LoginRequest;
import com.prime.service.payload.request.PasswordRequest;
import com.prime.service.payload.request.SigninRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordService passwordService;

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody @Valid SigninRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest request) {
        return userService.loginUser(request);
    }

    @PostMapping("/password/change")
    public ResponseEntity<?> passwordChange(@RequestBody @Valid PasswordRequest request) {
        return passwordService.changePassword(request);
    }


}
