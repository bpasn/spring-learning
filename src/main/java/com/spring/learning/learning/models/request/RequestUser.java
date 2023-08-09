package com.spring.learning.learning.models.request;

import lombok.Data;

@Data
public class RequestUser {
    private String email;
    private String password;
    private String name;
}
