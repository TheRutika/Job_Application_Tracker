package com.Rutika.job_application_tracker.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidException(MethodArgumentNotValidException ex)
    {
        Map<String, String> error = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err -> error.put(err.getField(), err.getDefaultMessage()));

        Map<String,Object> res = new HashMap<>();

        res.put("error",error);
        res.put("status",400);
        res.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(400).body(res);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUserNotFound(UserNotFoundException ex)
    {
        Map<String,String>error = new HashMap<>();

        error.put("error",ex.getMessage());

        Map<String,Object>res = new HashMap<>();

        res.put("error",error);
        res.put("status",404);
        res.put("timestamp",LocalDateTime.now());

        //return ResponseEntity.notFound().build();

        return ResponseEntity.status(404).body(res);
    }


    @ExceptionHandler(ApplicationNotFound.class)
    public ResponseEntity<Map<String,Object>>handleApplicationNotFound(ApplicationNotFound ap)
    {
        Map<String,String> errors = new HashMap<>();

        errors.put("error",ap.getMessage());

        Map<String,Object>res = new HashMap<>();
        res.put("error",errors);
        res.put("status",404);
        res.put("timestamp",LocalDateTime.now());

        return ResponseEntity.status(404).body(res);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String,Object>> emailAlreadyExist(EmailAlreadyExistException ex)
    {
        Map<String,String> errors = new HashMap<>();

        errors.put("error", ex.getMessage());

        Map<String,Object>res = new HashMap<>();

        res.put("error",errors);
        res.put("status",409); //CONFLICT
        res.put("timestamp",LocalDateTime.now());

        return ResponseEntity.status(409).body(res);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handlerqException(Exception ex)
    {
        Map<String,String> maps = new HashMap<>();

        maps.put("error","Something went wrong in server");
//        maps.put("error", ex.getMessage());  //OPTIONAL

        Map<String,Object>res = new HashMap<>();
        res.put("error",maps);
        res.put("status",500);
        res.put("timestamp",LocalDateTime.now());

        return ResponseEntity.status(500).body(res);
    }



}
