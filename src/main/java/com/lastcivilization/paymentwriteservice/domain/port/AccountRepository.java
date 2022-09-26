package com.lastcivilization.paymentwriteservice.domain.port;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountModel;

import java.util.Optional;

public interface AccountRepository {

    AccountModel save(AccountModel accountModel);

    Optional<AccountModel> findById(Long id);
}
