package com.spring.learning.learning.businessLogic;

import com.spring.learning.learning.entitys.UserEntity;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.mappers.MPUser;
import com.spring.learning.learning.models.request.RequestUser;
import com.spring.learning.learning.models.response.MResponseUser;
import com.spring.learning.learning.services.SUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BUser {
    private final SUser sUser;
    private final MPUser mpUser;

    public BUser(SUser sUser, MPUser mpUser) {
        this.sUser = sUser;
        this.mpUser = mpUser;
    }

    public MResponseUser register(RequestUser requestUser) throws BaseException {
        UserEntity entity = sUser.register(requestUser);
        return mpUser.toResponseUser(entity);
    }
}
