package com.spring.learning.learning.interfaces;

import com.spring.learning.learning.entitys.EnUser;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.models.request.RequestUser;

import java.util.Optional;

public interface IUser {
    EnUser create(RequestUser user) throws BaseException;
    Optional<EnUser> findByEmail(String email) throws BaseException;

    boolean matcherPassword(String password,String match);
}
