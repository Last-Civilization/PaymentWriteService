package com.lastcivilization.paymentwriteservice.domain;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;
import com.lastcivilization.paymentwriteservice.domain.dto.UserDto;
import com.lastcivilization.paymentwriteservice.domain.exception.AccountNotFoundException;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import com.lastcivilization.paymentwriteservice.domain.port.PaymentService;
import com.lastcivilization.paymentwriteservice.domain.port.UserService;

public class PaymentServiceImp implements PaymentService {

    private final UserService userService;
    private final AccountRepository accountRepository;

    public PaymentServiceImp(UserService userService, AccountRepository accountRepository) {
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    @Override
    public Long createAccount() {
        Account account = buildAccount();
        AccountDto accountDto = Mapper.toDto(account);
        AccountDto savedAccountDto = accountRepository.save(accountDto);
        return savedAccountDto.getId();
    }

    private Account buildAccount() {
        return Account.Builder.anAccount().build();
    }

    @Override
    public AccountDto charge(String keycloakId, int amount) {
        UserDto userDto = userService.getUser(keycloakId);
        AccountDto accountDto = getAccount(userDto.account());
        accountDto.setMoney(getMoneyAfterCharge(amount, accountDto));
        return accountRepository.save(accountDto);
    }

    private int getMoneyAfterCharge(int amount, AccountDto accountDto) {
        return accountDto.getMoney() - amount;
    }

    private AccountDto getAccount(long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    @Override
    public AccountDto give(String keycloakId, int amount) {
        UserDto userDto = userService.getUser(keycloakId);
        AccountDto accountDto = getAccount(userDto.account());
        accountDto.setMoney(getMoneyAfterGive(accountDto, amount));
        return accountRepository.save(accountDto);
    }

    private int getMoneyAfterGive(AccountDto accountDto, int amount) {
        return accountDto.getMoney() + amount;
    }
}
