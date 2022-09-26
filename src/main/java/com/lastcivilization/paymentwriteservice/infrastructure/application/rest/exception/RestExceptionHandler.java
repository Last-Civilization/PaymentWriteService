package com.lastcivilization.paymentwriteservice.infrastructure.application.rest.exception;

import com.lastcivilization.paymentwriteservice.domain.exception.AccountNotFoundException;
import com.lastcivilization.paymentwriteservice.domain.exception.ApplicationException;
import com.lastcivilization.paymentwriteservice.domain.exception.NotEnoughMoneyException;
import com.lastcivilization.paymentwriteservice.domain.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ErrorEntity handleAccountNotFoundException(AccountNotFoundException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    ErrorEntity handleApplicationException(ApplicationException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ErrorEntity handleUserNotFoundException(UserNotFoundException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    ErrorEntity handleNotEnoughMoneyException(NotEnoughMoneyException exception){
        return new ErrorEntity(exception.getMessage());
    }
}
