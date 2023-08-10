package com.spring.learning.learning.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

public class BaseException extends Exception{
    private HttpStatus status = HttpStatus.EXPECTATION_FAILED;

    public  BaseException(String code){
    super(code);
    }
    public BaseException(String code , HttpStatus status) {
        super(code);
        setStatus(status);
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public HttpStatus getStatus(){
        return this.status;
    }
}
