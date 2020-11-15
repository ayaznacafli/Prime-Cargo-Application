package com.prime.user.service.impl;

import com.prime.common.security.service.SecurityService;
import com.prime.user.exceptions.NewPasswordsNotMatchException;
import com.prime.user.exceptions.PasswordIsIncorrectException;
import com.prime.user.service.payload.request.PasswordRequest;
import com.prime.user.service.payload.response.MessageResponse;
import com.prime.user.repository.UserRepository;
import com.prime.user.service.PasswordService;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Import(SecurityService.class)
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;

    @Override
    public ResponseEntity<?> changePassword(PasswordRequest request) {
        Optional<String> currentUserLogin = securityService.getCurrentUserLogin();
        userRepository.findUserByUsernameIgnoreCase(currentUserLogin.get()).ifPresent(
                user -> {
                    if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
                        throw new PasswordIsIncorrectException();
                    }
                    checkIfNewPasswordsMatch(request.getNewPassword(), request.getMatchingNewPassword());
                    user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                    userRepository.save(user);
                });
        return ResponseEntity.ok(new MessageResponse("Password changed."));
    }

    private void checkIfNewPasswordsMatch(String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            throw new NewPasswordsNotMatchException();
        }
    }
}
