package com.lastcivilization.paymentwriteservice.domain;

import com.lastcivilization.paymentwriteservice.domain.view.AccountModel;

final class Mapper {

    private Mapper(){
        throw new RuntimeException("You can not create instance of this class!");
    }

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
