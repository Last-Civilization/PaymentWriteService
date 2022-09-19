package com.lastcivilization.paymentwriteservice.utils;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCreator {

    private final AccountRepository accountRepository;

    public AccountDto setTestAccountMoney(int money){
        AccountDto accountDto = AccountDto.Builder.anAccountDto()
                .id(1L)
                .money(money)
                .build();
        return accountRepository.save(accountDto);
    }
}