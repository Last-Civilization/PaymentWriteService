package com.lastcivilization.paymentwriteservice.domain;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;

class Mapper {

    static AccountDto toDto(Account account){
        return AccountDto.Builder.anAccountDto()
                .id(account.getId())
                .money(account.getMoney())
                .build();
    }
}
