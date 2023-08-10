package com.spring.learning.learning.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class CTestApi {

    @GetMapping
    public String test(){
        return "API IS READY USING";
    }

}
