package com.spring.learning.learning.interfaces;

import com.spring.learning.learning.entitys.UserEntity;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.mappers.MPUser;
import com.spring.learning.learning.models.request.RequestUser;

public interface IUser {
    UserEntity register(RequestUser user) throws BaseException;
}
