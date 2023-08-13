package com.spring.learning.learning.config;

import com.spring.learning.learning.config.token.AuthExecption;
import com.spring.learning.learning.config.token.TokenFilter;
import com.spring.learning.learning.services.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    private final TokenService tokenService;
    private final AuthExecption unauthorization;

    public SecurityConfig(TokenService tokenService, AuthExecption enpoint) {
        this.tokenService = tokenService;
        this.unauthorization = enpoint;
    }
    private final String[] PUBLIC = {
            "/api/user/register",
            "/api/user/login",
    };
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        TokenFilter filter = new TokenFilter(tokenService);

        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorization))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                    auth -> auth.requestMatchers(PUBLIC).permitAll().anyRequest().authenticated()
                ).addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
                

    return http.build();
    }
}
