package com.prime.user.service.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.prime.user.exceptions.EmailAlreadyUsedException;
import com.prime.user.exceptions.UsernameAlreadyUsedException;
import com.prime.user.model.Authority;
import com.prime.user.model.User;
import com.prime.common.security.model.ERole;
import com.prime.user.repository.AuthorityRepository;
import com.prime.user.repository.UserRepository;
import com.prime.user.service.payload.request.LoginRequest;
import com.prime.user.service.payload.request.SigninRequest;
import com.prime.user.service.payload.response.MessageResponse;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    public static final String USERNAME = "ayaznacafli";
    public static final String PASSWORD = "Ayaz1997!";
    public static final String MATCHING_PASSWORD = "Ayaz1997!";
    public static final String EMAIL = "ayaz.nacafli@mail.ru";
    public static final String FIRST_NAME = "Ayaz";
    public static final String LAST_NAME = "Nacafli";
    public static final String MESSAGE = "Sended confirm message your email";

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthorityRepository authorityRepository;
    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private AuthenticationProvider provider;
    @Mock
    private PasswordEncoder passwordEncoder;

    private SigninRequest signinRequest;
    private LoginRequest loginRequest;
    private final Set<Authority> authorities = new HashSet<>();
    private Authority authority;
    private User user;

    @Before
    public void setUp() {
        loginRequest = LoginRequest.builder()
                .username(USERNAME)
                .password(PASSWORD).build();
        signinRequest = SigninRequest.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .matchingPassword(MATCHING_PASSWORD).build();
        authority = Authority.builder()
                .id(1L)
                .name(ERole.USER).build();
        authorities.add(authority);
        user = User.builder()
                .authorities(authorities)
                .username(loginRequest.getUsername())
                .password(loginRequest.getPassword()).build();
    }

    @Test
    public void giveValidSigninRequestisOk() {
        when(userRepository.findUserByUsernameIgnoreCase(signinRequest.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findUserByEmailIgnoreCase(signinRequest.getEmail())).thenReturn(Optional.empty());
        when(authorityRepository.findAuthorityByName(any())).thenReturn(Optional.of(authority));
        when(passwordEncoder.encode(signinRequest.getPassword())).thenReturn("encode");
        when(userRepository.save(user)).thenReturn(user);
        User response = userRepository.save(user);
        assertThat(response.getUsername()).isEqualTo(user.getUsername());
        ResponseEntity<?> result = userService.createUser(signinRequest);
        MessageResponse message = (MessageResponse) result.getBody();
        assertThat(message.getMessage()).isEqualTo(MESSAGE);
        verify(userRepository).findUserByUsernameIgnoreCase(signinRequest.getUsername());
        verify(userRepository).findUserByEmailIgnoreCase(signinRequest.getEmail());
        verify(authorityRepository).findAuthorityByName(any());
        verify(passwordEncoder).encode(signinRequest.getPassword());
    }

    @Test
    public void whenClientWithUsernameAlreadyExistThenBadRequest() {
        when(userRepository.findUserByUsernameIgnoreCase(signinRequest.getUsername()))
                .thenReturn(Optional.of(user));
        when(userRepository.findUserByEmailIgnoreCase(signinRequest.getEmail())).thenReturn(Optional.empty());
        when(authorityRepository.findAuthorityByName(any())).thenReturn(Optional.of(authority));
        when(passwordEncoder.encode(signinRequest.getPassword())).thenReturn("encode");
        when(userRepository.save(user)).thenReturn(user);
        assertThatThrownBy(() -> userService.createUser(signinRequest))
                .isInstanceOf(UsernameAlreadyUsedException.class);
        verify(userRepository).findUserByUsernameIgnoreCase(signinRequest.getUsername());
    }

    @Test
    public void whenClientWithUEmailAlreadyExistThenBadRequest() {
        when(userRepository.findUserByUsernameIgnoreCase(signinRequest.getUsername()))
                .thenReturn(Optional.empty());
        when(userRepository.findUserByEmailIgnoreCase(signinRequest.getEmail())).thenReturn(Optional.of(user));
        when(authorityRepository.findAuthorityByName(any())).thenReturn(Optional.of(authority));
        when(passwordEncoder.encode(signinRequest.getPassword())).thenReturn("encode");
        when(userRepository.save(user)).thenReturn(user);
        assertThatThrownBy(() -> userService.createUser(signinRequest))
                .isInstanceOf(EmailAlreadyUsedException.class);
        verify(userRepository).findUserByUsernameIgnoreCase(signinRequest.getUsername());
        verify(userRepository).findUserByUsernameIgnoreCase(signinRequest.getUsername());
        verify(userRepository).findUserByEmailIgnoreCase(signinRequest.getEmail());
    }


    @Test
    public void giveExistingUsernameAndPassword() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword());
        authenticationManager.authenticate(authentication);
        when(authenticationManagerBuilder.getObject()
                .authenticate(authentication)).thenReturn(authentication);
    }


}
