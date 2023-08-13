package com.spring.learning.learning.config.token;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.spring.learning.learning.config.SecurityConfig;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.exceptions.UnauthroizationExecption;
import com.spring.learning.learning.services.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenFilter extends GenericFilterBean {

    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest http = (HttpServletRequest) request;

        String authorization = http.getHeader("Authorization");

        if(ObjectUtils.isEmpty(authorization)) {
            chain.doFilter(request,response);
            return;
        }

        if(!authorization.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }

        String token = authorization.substring(7);

        DecodedJWT decode = tokenService.verify(token);
        if(decode == null){
            chain.doFilter(request, response);
           return;
        }

        String principal = decode.getClaim("principal").asString();
        String role = decode.getClaim("role").asString();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, "(protected)", authorities);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        chain.doFilter(request,response);
    }
}
