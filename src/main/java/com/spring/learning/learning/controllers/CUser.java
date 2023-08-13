package com.spring.learning.learning.controllers;

import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.models.request.RequestLogin;
import com.spring.learning.learning.models.request.RequestUser;
import com.spring.learning.learning.models.response.MResponseToken;
import com.spring.learning.learning.models.response.MResponseUser;
import com.spring.learning.learning.services.SUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class CUser {

    private final SUser sUser;

    public CUser(SUser sUser) {
        this.sUser = sUser;
    }

    @GetMapping("test")
    public String testApi() {

        return "Api is ready";
    }

    @PostMapping("register")
    public ResponseEntity<MResponseUser> register(@RequestBody RequestUser requestUser) throws BaseException {
        MResponseUser response = sUser.create(requestUser);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("login")
    public ResponseEntity<MResponseToken> login (@RequestBody RequestLogin requestLogin) throws BaseException {
        return ResponseEntity.ok(sUser.login(requestLogin.getEmail(),requestLogin.getPassword()));
    }
}
