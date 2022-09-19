package com.lastcivilization.paymentwriteservice.infrastructure.application.rest.exception;

import com.lastcivilization.paymentwriteservice.domain.exception.AccountNotFoundException;
import com.lastcivilization.paymentwriteservice.domain.exception.ApplicationException;
import com.lastcivilization.paymentwriteservice.domain.exception.NotEnoughMoneyException;
import com.lastcivilization.paymentwriteservice.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    ResponseEntity<ErrorEntity> handleAccountNotFoundException(AccountNotFoundException exception){
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new ErrorEntity(
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(ApplicationException.class)
    ResponseEntity<ErrorEntity> handleApplicationException(ApplicationException exception){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(new ErrorEntity(
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ErrorEntity> handleUserNotFoundException(UserNotFoundException exception){
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new ErrorEntity(
                        exception.getMessage()
                ));
    }

    @ExceptionHandler
    ResponseEntity<ErrorEntity> handleNotEnoughMoneyException(NotEnoughMoneyException exception){
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorEntity(
                        exception.getMessage()
                ));
    }
}
