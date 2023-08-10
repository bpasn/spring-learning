package com.spring.learning.learning.businessLogic;

import com.spring.learning.learning.entitys.EnUser;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.mappers.MPUser;
import com.spring.learning.learning.models.request.RequestUser;
import com.spring.learning.learning.models.response.MResponseUser;
import com.spring.learning.learning.services.SUser;
import com.spring.learning.learning.services.TokenService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class BUser {
    private final SUser sUser;
    private final MPUser mpUser;
    private final TokenService tokenService;




    public BUser(SUser sUser, MPUser mpUser, TokenService tokenService) {
        this.sUser = sUser;
        this.mpUser = mpUser;
        this.tokenService = tokenService;
    }

    public MResponseUser register(RequestUser requestUser) throws BaseException {
        EnUser entity = sUser.create(requestUser);
        return mpUser.toResponseUser(entity);
    }

    public String login(String email, String password) throws BaseException {
        Optional<EnUser> opt = sUser.findByEmail(email);
        if (opt.isEmpty()) {
            throw new BaseException("Email or password is incorrect!", HttpStatus.BAD_REQUEST);
        }
        EnUser user = opt.get();
        if (!sUser.matcherPassword(password,user.getPassword())){
            throw new BaseException("Password is incorrect!",HttpStatus.BAD_REQUEST);
        }
        return tokenService.generateToken(user);
    }
}
