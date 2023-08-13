package com.spring.learning.learning.exceptions;

import java.io.IOException;

public class UnauthroizationExecption extends IOException {
    public UnauthroizationExecption(String code){
        super(code);
    }
}
