package com.baeldung.lsc.web.errors;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataRetrievalFailureException.class)
    public ResponseEntity<String> handleDataRetrievalException(DataRetrievalFailureException ex) {
        return new ResponseEntity<String>("Exception retrieving data: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<String> handleNotificationBadRequest(FeignException.BadRequest ex) {
        return new ResponseEntity<String>("Notification rejected: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
        HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>("Media Type not supported: " + ex.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

}
