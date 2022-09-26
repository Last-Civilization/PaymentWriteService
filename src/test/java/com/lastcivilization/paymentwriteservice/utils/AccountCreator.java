package com.lastcivilization.paymentwriteservice.utils;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountModel;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCreator {

    private final AccountRepository accountRepository;

    public AccountModel setTestAccountMoney(int money){
        AccountModel accountModel = new AccountModel(
                1L,
                money
        );
        return accountRepository.save(accountModel);
    }
}