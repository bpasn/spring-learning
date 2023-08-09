package com.spring.learning.learning.services;

import com.spring.learning.learning.entitys.UserEntity;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.interfaces.IUser;
import com.spring.learning.learning.mappers.MPUser;
import com.spring.learning.learning.models.request.RequestUser;
import com.spring.learning.learning.repositorys.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SUser implements IUser {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity register(RequestUser user) throws BaseException {
        if (Objects.isNull(user.getEmail())) throw new BaseException("api.create.email.null", HttpStatus.BAD_REQUEST);
        if (Objects.isNull(user.getPassword())) throw new BaseException("api.create.password.null",HttpStatus.BAD_REQUEST);
        if (Objects.isNull(user.getName())) throw new BaseException("api.create.name.null",HttpStatus.BAD_REQUEST);

        if (userRepository.existsByEmail(user.getEmail())) throw new BaseException("api.create.email.duplicate",HttpStatus.CONFLICT);

        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setName(user.getName());

        return userRepository.save(entity);
    }
}
