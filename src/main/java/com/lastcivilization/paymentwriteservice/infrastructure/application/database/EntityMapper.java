package com.lastcivilization.paymentwriteservice.infrastructure.application.database;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface EntityMapper {

    EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

    AccountDto toDto(AccountEntity accountEntity);
    AccountEntity toEntity(AccountDto accountDto);
}
