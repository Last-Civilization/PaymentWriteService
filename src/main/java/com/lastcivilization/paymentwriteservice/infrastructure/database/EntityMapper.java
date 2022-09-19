package com.lastcivilization.paymentwriteservice.infrastructure.database;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapper {

    EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

    AccountDto toDto(AccountEntity accountEntity);
    AccountEntity toEntity(AccountDto accountDto);
}
