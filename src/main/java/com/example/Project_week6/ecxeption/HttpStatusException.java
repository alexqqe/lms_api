package com.example.Project_week6.ecxeption;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpStatusException extends RuntimeException{

    private final HttpStatus httpStatus;

    public HttpStatusException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
    }
}
