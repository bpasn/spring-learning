package com.spring.learning.learning.mappers;

import com.spring.learning.learning.entitys.EnUser;
import com.spring.learning.learning.models.response.MResponseUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MPUser {
    MResponseUser toResponseUser(EnUser u);
}
