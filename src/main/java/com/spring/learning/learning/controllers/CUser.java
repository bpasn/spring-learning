package com.spring.learning.learning.controllers;

import com.spring.learning.learning.businessLogic.BUser;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.models.request.RequestUser;
import com.spring.learning.learning.models.response.MResponseUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class CUser {

    private final BUser bUser;

    public CUser(BUser bUser) {
        this.bUser = bUser;
    }

    @GetMapping("test")
    public String testApi() {
        return "Api is ready";
    }

    @PostMapping("register")
    public ResponseEntity<MResponseUser> register(@RequestBody RequestUser requestUser) throws BaseException {
        MResponseUser response = bUser.register(requestUser);
        return ResponseEntity.ok(response);
    }
}
