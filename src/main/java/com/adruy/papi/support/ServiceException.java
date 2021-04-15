package com.adruy.papi.support;

import com.adruy.papi.application.ProductsNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebInputException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ControllerAdvice
public class ServiceException {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceException.class);

    public static final String INTERNAL_SERVER_ERROR_MSG = "Invalid request";
    public static final String PRODUCTS_NOT_FOUND = "Products Not Found";
    public static final String INVALID_REQUEST_CONTENT = "Invalid Request Content";
    public static final String INVALID_REQUEST_PARAMETER = "Invalid Request Parameter";

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handle(IllegalArgumentException ex) {
        LOG.error(INVALID_REQUEST_PARAMETER, ex);
        return new ResponseEntity<>(new ErrorResponse(BAD_REQUEST, ex.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handle(ServerWebInputException ex) {
        LOG.error(INVALID_REQUEST_CONTENT, ex);
        final var message = Optional.of(ex.getMethodParameter())
                .map(param -> param.getParameter().getName() + " passed is invalid")
                .orElse(INVALID_REQUEST_CONTENT);
        return new ResponseEntity<>(new ErrorResponse(BAD_REQUEST, message), BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handle(ProductsNotFoundException ex) {
        LOG.error(PRODUCTS_NOT_FOUND, ex);
        return new ResponseEntity<>(new ErrorResponse(NO_CONTENT, ex.getMessage()), NO_CONTENT);
    }

    /* We can handle more exception types here */

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handle(Exception ex) {
        LOG.error(INTERNAL_SERVER_ERROR_MSG, ex);
        return new ResponseEntity<>(new ErrorResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG), INTERNAL_SERVER_ERROR);
    }
}
