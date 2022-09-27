package com.lastcivilization.paymentwriteservice.infrastructure.database;

import com.lastcivilization.paymentwriteservice.domain.view.AccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapper {

    EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

    AccountModel toDto(AccountEntity accountEntity);
    AccountEntity toEntity(AccountModel accountModel);
}
