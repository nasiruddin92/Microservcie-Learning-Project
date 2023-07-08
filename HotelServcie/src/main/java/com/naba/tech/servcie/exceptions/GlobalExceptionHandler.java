package com.naba.tech.servcie.exceptions;


import com.naba.tech.servcie.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
//        String message=ex.getMessage();
//        ApiResponse response=ApiResponse.builder()
//                .message(message)
//                .success(true)
//                .status( HttpStatus.NOT_FOUND)
//                .build();
//        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<Map<String,Object>> handleResourceNotFoundException(ResourceNotFoundException ex){

        Map<String,Object> map=new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("success",true);
        map.put("status",HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
