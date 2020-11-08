package com.prime.service.impl;

import com.prime.common.security.model.ERole;
import com.prime.common.security.service.JwtService;
import com.prime.exceptions.AuthorityNotFoundException;
import com.prime.exceptions.EmailAlreadyUsedException;
import com.prime.exceptions.UsernameAlreadyUsedException;
import com.prime.model.Authority;
import com.prime.model.User;
import com.prime.model.enumeration.UserStatus;
import com.prime.repository.AuthorityRepository;
import com.prime.repository.UserRepository;
import com.prime.service.UserService;
import com.prime.service.payload.request.LoginRequest;
import com.prime.service.payload.request.SigninRequest;
import com.prime.service.payload.response.JwtResponse;
import com.prime.service.payload.response.MessageResponse;
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                .ifPresent(user -> { throw new UsernameAlreadyUsedException(request.getUsername()); });
        userRepository.findUserByEmailIgnoreCase(request.getEmail())
                .ifPresent(user -> { throw new EmailAlreadyUsedException(request.getEmail()); });
        Set<Authority> authorities = createAuthorities(ERole.USER);
        User user = createUserEntityObject(request, authorities);

        user.setCreationDate(LocalDateTime.now());
        user.setStatus(UserStatus.ACTIVE);
        //Confirm email and make active status
        User result = userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Sended confirm message your email"));
    }

    @Override
    public ResponseEntity<?> loginUser(LoginRequest request) {
        log.trace("Authenticating user {}", request.getUsername());
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword());

        System.out.println(0);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println(1);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(2);
        Set<String> authority = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());
        System.out.println(3);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Duration duration = getDuration(request.getRememberMe());
        String jwt = jwtService.issueToken(authentication, duration);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);

        return new ResponseEntity<>(new JwtResponse(jwt,userDetails.getId(),userDetails.getEmail(),authority), httpHeaders, HttpStatus.OK);
    }

    private Set<Authority> createAuthorities(ERole... authoritiesString) {
        Set<Authority> authorities = new HashSet<>();
        for (ERole authorityString : authoritiesString) {
           authorities.add(authorityRepository.findAuthorityByName(authorityString).orElseThrow(() ->
                    new AuthorityNotFoundException(authorityString)));
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
