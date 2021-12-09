package com.example.filestorageapp.handler;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.example.filestorageapp.model.ErrorBag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice @Slf4j
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AmazonS3Exception.class})
    public ResponseEntity<Object> handleAmazonS3Exceptions(AmazonS3Exception exception, WebRequest request) {
        ErrorBag errorBag = new ErrorBag()
                .setMessage(exception.getMessage())
                .setStatus(HttpStatus.BAD_REQUEST.value());

        ResponseEntity<Object> entity = new ResponseEntity<>(errorBag, HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<Object> handleIOExceptions(IOException exception, WebRequest request) {
        ErrorBag errorBag = new ErrorBag()
                                    .setMessage(exception.getMessage())
                                    .setStatus(HttpStatus.BAD_REQUEST.value());

        ResponseEntity<Object> entity = new ResponseEntity<>(errorBag, HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest request) {
        ErrorBag errorBag = new ErrorBag()
                .setMessage("Error upload file")
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(exception.getMessage());

        ResponseEntity<Object> entity = new ResponseEntity<>(errorBag, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @ExceptionHandler({AmazonServiceException.class})
    public ResponseEntity<Object> handleAmazonServiceExceptions(AmazonServiceException exception, WebRequest request) {
        ErrorBag errorBag = new ErrorBag()
                .setMessage(exception.getMessage())
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        ResponseEntity<Object> entity = new ResponseEntity<>(errorBag, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
}
