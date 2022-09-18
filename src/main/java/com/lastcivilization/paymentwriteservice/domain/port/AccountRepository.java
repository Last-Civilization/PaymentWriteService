package com.lastcivilization.paymentwriteservice.domain.port;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;

import java.util.Optional;

public interface AccountRepository {

    AccountDto save(AccountDto accountDto);

    Optional<AccountDto> findById(Long id);
}
