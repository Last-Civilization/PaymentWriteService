package com.lastcivilization.paymentwriteservice.domain;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import com.lastcivilization.paymentwriteservice.domain.port.AccountService;
import com.lastcivilization.paymentwriteservice.domain.port.UserService;

public class AccountServiceImp implements AccountService {

    private final UserService userService;
    private final AccountRepository accountRepository;

    public AccountServiceImp(UserService userService, AccountRepository accountRepository) {
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    @Override
    public Long createAccount() {
        return null;
    }

    @Override
    public AccountDto charge(String keycloakId, int amount) {
        return null;
    }

    @Override
    public AccountDto give(String keycloakId, int amount) {
        return null;
    }
}
