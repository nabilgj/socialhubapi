package com.revature.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class InvalidUserException extends RuntimeException {

    public InvalidUserException(){
        super("The user name or email are invalid");
    }
}
