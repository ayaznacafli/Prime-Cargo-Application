package com.prime.security;

import com.prime.exceptions.UserIsNotActiveException;
import com.prime.model.CustomSpringSecurityUser;
import com.prime.model.User;
import com.prime.model.enumeration.UserStatus;
import com.prime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Authenticating {}", username);

        String lowercaseUsername = username.toLowerCase(Locale.ENGLISH);
        return userRepository.findOneWithAuthoritiesByUsername(lowercaseUsername)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("User %s was not found in the database", lowercaseUsername)));
    }

    private CustomSpringSecurityUser createSpringSecurityUser(User user) {
        checkUserProfileStatus(user);
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
        return new CustomSpringSecurityUser(user.getUsername(),user.getPassword(),grantedAuthorities);
    }

    private void checkUserProfileStatus(User user) throws UserIsNotActiveException {
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new UserIsNotActiveException();
        }
    }
}
