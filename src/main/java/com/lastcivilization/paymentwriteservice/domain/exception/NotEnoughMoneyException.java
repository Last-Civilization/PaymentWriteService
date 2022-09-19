package com.lastcivilization.paymentwriteservice.domain.exception;

public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException(int newMoney) {
        super("Not enough money to charge the account! Balance after charge: %d".formatted(newMoney));
    }
}
