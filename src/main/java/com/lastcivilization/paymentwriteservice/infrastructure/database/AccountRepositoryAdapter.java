package com.lastcivilization.paymentwriteservice.infrastructure.database;

import com.lastcivilization.paymentwriteservice.domain.view.AccountModel;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lastcivilization.paymentwriteservice.infrastructure.database.EntityMapper.MAPPER;

@Service
@RequiredArgsConstructor
class AccountRepositoryAdapter implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public AccountModel save(AccountModel accountModel) {
        AccountEntity accountEntity = MAPPER.toEntity(accountModel);
        AccountEntity savedAccountEntity = accountJpaRepository.save(accountEntity);
        return MAPPER.toDto(savedAccountEntity);
    }

    @Override
    public Optional<AccountModel> findById(Long id) {
        Optional<AccountEntity> accountEntity = accountJpaRepository.findById(id);
        return accountEntity
                .map(MAPPER::toDto);
    }

    @Override
    public void deleteById(long id) {
        accountJpaRepository.deleteById(id);
    }
}
