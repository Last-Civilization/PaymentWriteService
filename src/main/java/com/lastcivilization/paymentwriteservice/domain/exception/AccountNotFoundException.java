package com.lastcivilization.paymentwriteservice.domain.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(long accountId) {
        super("Can not found account with id: %d".formatted(accountId));
    }
}
