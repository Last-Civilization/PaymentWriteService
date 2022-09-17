package com.lastcivilization.paymentwriteservice.domain.port;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;

interface AccountService {

    Long createAccount();
    AccountDto charge(String keycloakId, int amount);
    AccountDto give(String keycloakId, int amount);
}
