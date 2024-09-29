package com.smcbrmm.microservice.payment.Handler;

import com.smcbrmm.microservice.payment.Constant.ApiStatus;
import com.smcbrmm.microservice.payment.Dto.Response;
import com.smcbrmm.microservice.payment.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Response<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new Response<>(HttpStatus.NOT_FOUND, ApiStatus.ERROR, List.of(ex.getMessage()));
    }
}
