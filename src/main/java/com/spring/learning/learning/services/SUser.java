package com.spring.learning.learning.services;

import com.spring.learning.learning.entitys.EnUser;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.implementses.IUser;
import com.spring.learning.learning.mappers.MPUser;
import com.spring.learning.learning.models.request.RequestUser;
import com.spring.learning.learning.models.response.MResponseToken;
import com.spring.learning.learning.models.response.MResponseUser;
import com.spring.learning.learning.repositorys.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SUser implements IUser {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenService sToken;

    private final MPUser mpUser;


    public SUser(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService sToken, MPUser mpUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sToken = sToken;
        this.mpUser = mpUser;
    }

    @Override
    public MResponseUser create(RequestUser user) throws BaseException {
        if (Objects.isNull(user.getEmail())) throw new BaseException("api.create.email.null", HttpStatus.BAD_REQUEST);
        if (Objects.isNull(user.getPassword())) throw new BaseException("api.create.password.null",HttpStatus.BAD_REQUEST);
        if (Objects.isNull(user.getName())) throw new BaseException("api.create.name.null",HttpStatus.BAD_REQUEST);

        if (userRepository.existsByEmail(user.getEmail())) throw new BaseException("api.create.email.duplicate",HttpStatus.CONFLICT);

        EnUser entity = new EnUser();
        entity.setEmail(user.getEmail());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setName(user.getName());

        return mpUser.toResponseUser(userRepository.save(entity));
    }

    @Override
    public MResponseToken login(String email , String password) throws BaseException {
        MResponseToken response = new MResponseToken();
        Optional<EnUser> opt = userRepository.findByEmail(email);
        if(opt.isEmpty()) throw new BaseException("auth.email.or.password.incorrect",HttpStatus.BAD_REQUEST);

        EnUser user = opt.get();

        if(!matcherPassword(password,user.getPassword())) throw new BaseException("auth.email.or.password.incorrect",HttpStatus.BAD_REQUEST);

        String token = sToken.generateToken(user);
        response.setToken(token);
        return response;
    }

    @Override
    public Optional<EnUser> findByEmail(String email) throws BaseException {
        return userRepository.findByEmail(email);
    }




    @Override
    public boolean matcherPassword(String password, String match) {
        return passwordEncoder.matches(password,match);
    }
}
