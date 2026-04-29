package com.example.Project_week6.ecxeption;

import com.example.Project_week6.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class    CommonExceptionHandler {

    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<Error> handleHttpStatusEx(HttpStatusException e){
        return ResponseEntity.status(e.getHttpStatus()).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        HashMap<String, Object> errors = new HashMap<>();

        errors.put("message", "Ошибка в валидации");

        List<HashMap> response = new ArrayList<>();

        for (FieldError fieldError: e.getBindingResult().getFieldErrors()){
            HashMap<String, String> field = new HashMap<>();
            field.put("field", fieldError.getField());
            field.put("error", fieldError.getDefaultMessage());
            response.add(field);
        }

        errors.put("details", response);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler (Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
    }

}
