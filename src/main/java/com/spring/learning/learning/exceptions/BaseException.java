package com.spring.learning.learning.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
public  class BaseException extends Exception {
    private HttpStatus status = HttpStatus.EXPECTATION_FAILED;

    public BaseException(String code) {
        super(code);
        setStatus(HttpStatus.EXPECTATION_FAILED);
    }

    public BaseException(String code , HttpStatus status){
        super(code);
        setStatus(status);
    }


}
