package com.spring.learning.learning.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spring.learning.learning.entitys.EnUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;


@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public String generateToken(EnUser user){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date expiresAt = calendar.getTime();

        return JWT
                .create()
                .withIssuer(issuer)
                .withClaim("principal", user.getId())
                .withClaim("role", "USER")
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256((String) secret));
    }


    public DecodedJWT verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).withIssuer(issuer).build();
            return verifier.verify(token);
        }catch (Exception ex){
            return null;
        }
    }
}
