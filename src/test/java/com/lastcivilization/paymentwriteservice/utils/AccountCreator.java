package com.lastcivilization.paymentwriteservice.utils;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCreator {

    private final AccountRepository accountRepository;

    AccountDto createAccount(long id, int money){
        AccountDto accountDto = AccountDto.Builder.anAccountDto()
                .id(id)
                .money(money)
                .build();
        return accountRepository.save(accountDto);
    }
}
