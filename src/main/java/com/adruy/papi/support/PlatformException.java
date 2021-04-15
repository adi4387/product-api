package com.adruy.papi.support;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class PlatformException {

    public static final String INVALID_DATA_PASSED = "Invalid data passed";

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handle(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ErrorResponse(BAD_REQUEST, ex.getMessage()), BAD_REQUEST);
    }
}
