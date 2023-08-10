package com.spring.learning.learning.interfaces;

import com.spring.learning.learning.entitys.EnUser;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.models.request.RequestUser;

public interface IUser {
    EnUser register(RequestUser user) throws BaseException;
    String login(String email,String password) throws BaseException;
}
