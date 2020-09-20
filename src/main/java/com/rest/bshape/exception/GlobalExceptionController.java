package com.rest.bshape.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@ControllerAdvice
@Slf4j
public class GlobalExceptionController {

    // handling specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // handling global exception
  /*  @ExceptionHandler(Exception.class) // brała kazdy wyjątek i zagłuszała je :(
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/


    /*  @ExceptionHandler(Exception.class)*/ // brała kazdy wyjątek i zagłuszała je :(
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorDetails globalExceptionHandling(Exception exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails EmptyResultDataAccessExceptionHandle(Exception exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
    }


}
