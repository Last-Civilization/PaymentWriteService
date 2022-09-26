package com.lastcivilization.paymentwriteservice.infrastructure.application.rest;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountModel;
import com.lastcivilization.paymentwriteservice.infrastructure.application.rest.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface RestMapper {

    RestMapper MAPPER = Mappers.getMapper(RestMapper.class);

    AccountDto toDto(AccountModel accountModel);
}
