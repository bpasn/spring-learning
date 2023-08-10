package com.spring.learning.learning.config.token;

import com.spring.learning.learning.services.TokenService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenFilterConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenService service;

    public TokenFilterConfigure(TokenService service){
        this.service = service;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        TokenFilter filter = new TokenFilter(service);
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
