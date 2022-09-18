package com.lastcivilization.paymentwriteservice.infrastructure.application.database;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lastcivilization.paymentwriteservice.infrastructure.application.database.EntityMapper.MAPPER;

@Service
@RequiredArgsConstructor
class AccountRepositoryAdapter implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public AccountDto save(AccountDto accountDto) {
        AccountEntity accountEntity = MAPPER.toEntity(accountDto);
        AccountEntity savedAccountEntity = accountJpaRepository.save(accountEntity);
        return MAPPER.toDto(savedAccountEntity);
    }

    @Override
    public Optional<AccountDto> findById(Long id) {
        Optional<AccountEntity> accountEntity = accountJpaRepository.findById(id);
        return accountEntity
                .map(MAPPER::toDto);
    }
}
