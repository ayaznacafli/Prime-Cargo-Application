package com.prime.config;

import com.prime.common.security.config.AuthenticationEntryPointConfigurer;
import com.prime.common.security.config.BaseSecurityConfig;
import com.prime.common.security.model.SecurityProperties;
import com.prime.common.security.service.JwtService;
import com.prime.common.security.service.TokenAuthService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Slf4j
@EnableWebSecurity
@Import({SecurityProperties.class, JwtService.class,
        AuthenticationEntryPointConfigurer.class})
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends BaseSecurityConfig {

    public SecurityConfiguration(JwtService jwtService) {
        super(List.of(new TokenAuthService(jwtService)));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").permitAll();

        super.configure(http);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

}
