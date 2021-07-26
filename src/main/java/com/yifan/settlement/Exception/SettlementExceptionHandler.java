package com.yifan.settlement.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@RestControllerAdvice
public class SettlementExceptionHandler  extends ResponseEntityExceptionHandler {
    public static final Logger LOGGER = Logger.getLogger(SettlementExceptionHandler.class.getName());

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.severe(ex.getMessage());

        return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(SettlementApiException.class)
    public ResponseEntity<SettlementErrorResponse> handleSettlementException(SettlementApiException exception, WebRequest request){
        LOGGER.severe("The error msg is : "+exception.getErrorCodeAndMsg().getErrorMsg());
        SettlementErrorResponse errorResponse = new SettlementErrorResponse(exception.getErrorCodeAndMsg().getCode(),exception.getHttpStatus(), LocalDateTime.now());
        return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
    }
}
