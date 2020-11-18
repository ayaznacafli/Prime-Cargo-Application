package com.prime.user.service.impl;

import com.prime.common.security.model.ERole;
import com.prime.common.security.service.JwtService;
import com.prime.user.exceptions.AuthorityNotFoundException;
import com.prime.user.exceptions.EmailAlreadyUsedException;
import com.prime.user.exceptions.UsernameAlreadyUsedException;
import com.prime.user.model.Authority;
import com.prime.user.model.User;
import com.prime.user.model.enumeration.UserStatus;
import com.prime.user.service.payload.request.LoginRequest;
import com.prime.user.service.payload.request.SigninRequest;
import com.prime.user.service.payload.response.JwtResponse;
import com.prime.user.service.payload.response.MessageResponse;
import com.prime.user.repository.AuthorityRepository;
import com.prime.user.repository.UserRepository;
import com.prime.user.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private static final Duration ONE_DAY = Duration.ofDays(1);
    private static final Duration ONE_WEEK = Duration.ofDays(7);

    private final JwtService jwtService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @Override
    @Transactional
    public ResponseEntity<?> createUser(SigninRequest request) {
        log.trace("Registration user {}", request.getUsername());
        userRepository.findUserByUsernameIgnoreCase(request.getUsername())
                .ifPresent(user -> {
                    throw new UsernameAlreadyUsedException(request.getUsername());
                });
        userRepository.findUserByEmailIgnoreCase(request.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyUsedException(request.getEmail());
                });
        Set<Authority> authorities = createAuthorities(ERole.USER);
        User user = createUserEntityObject(request, authorities);

        user.setCreationDate(LocalDateTime.now());
        user.setStatus(UserStatus.ACTIVE);
        //Confirm email and make active status
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Sended confirm message your email"));
    }

    @Override
    public ResponseEntity<?> loginUser(LoginRequest request) {
        log.trace("Authenticating user {}", request.getUsername());
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> authority = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Duration duration = getDuration(request.getRememberMe());
        String jwt = jwtService.issueToken(authentication, duration);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);

        return new ResponseEntity<>(new JwtResponse(jwt,userDetails.getId(),
                userDetails.getEmail(),authority), httpHeaders, HttpStatus.OK);
    }

    public Set<Authority> createAuthorities(ERole... authoritiesString) {
        Set<Authority> authorities = new HashSet<>();
        for (ERole authorityString : authoritiesString) {
            Authority authority = authorityRepository
                    .findAuthorityByName(authorityString)
                    .orElseThrow(() -> new AuthorityNotFoundException(authorityString));
            authorities.add(authority);
        }
        return authorities;
    }

    private User createUserEntityObject(SigninRequest request, Set<Authority> authorities) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAuthorities(authorities);
        return user;
    }

    private Duration getDuration(Boolean rememberMe) {
        return ((rememberMe != null) && rememberMe) ? ONE_WEEK : ONE_DAY;
    }
}
