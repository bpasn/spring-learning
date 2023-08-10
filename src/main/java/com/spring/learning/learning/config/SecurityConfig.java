package com.spring.learning.learning.config;

import com.spring.learning.learning.config.token.TokenFilterConfigure;
import com.spring.learning.learning.services.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    private final TokenService tokenService;

    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    private final String[] PUBLIC = {
            "/api/actuator/**",
            "/api/user/register",
            "/api/user/login",
            "/api/user/activate",
            "/api/user/resend-activation-email",
            "/api/socket/**"
    };
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> c.disable())
                .csrf(c -> c.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests().requestMatchers(PUBLIC).anonymous()
                .anyRequest().authenticated()
                .and().apply(new TokenFilterConfigure(tokenService));

    return http.build();
    }
}
