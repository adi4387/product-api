package com.adruy.papi.support;

import com.adruy.papi.application.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
class DomainExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DomainExceptionHandler.class);

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handle(DomainException ex) {
        return new ResponseEntity<>(new ErrorResponse(BAD_REQUEST, ex.getMessage()), BAD_REQUEST);
    }

}
