package com.lastcivilization.paymentwriteservice.domain;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountModel;

class Mapper {

    static AccountModel toDto(Account account){
        return new AccountModel(
                account.getId(),
                account.getMoney()
        );
    }

    public static Account toDomain(AccountModel accountModel) {
        return Account.Builder.anAccount()
                .id(accountModel.id())
                .money(accountModel.money())
                .build();
    }
}
