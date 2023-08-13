package com.spring.learning.learning.implementses;

import com.spring.learning.learning.entitys.EnUser;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.models.request.RequestLogin;
import com.spring.learning.learning.models.request.RequestUser;
import com.spring.learning.learning.models.response.MResponseToken;
import com.spring.learning.learning.models.response.MResponseUser;

import java.util.Optional;

public interface IUser {
    MResponseUser create(RequestUser user) throws BaseException;
    MResponseToken login(String email , String password) throws BaseException;
    Optional<EnUser> findByEmail(String email) throws BaseException;

    boolean matcherPassword(String password,String match);
}
