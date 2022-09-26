package com.lastcivilization.paymentwriteservice.domain;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountModel;
import com.lastcivilization.paymentwriteservice.domain.exception.AccountNotFoundException;
import com.lastcivilization.paymentwriteservice.domain.exception.NotEnoughMoneyException;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import com.lastcivilization.paymentwriteservice.domain.port.UserService;
import com.lastcivilization.paymentwriteservice.domain.port.dto.UserDto;

import static com.lastcivilization.paymentwriteservice.domain.Mapper.toDomain;
import static com.lastcivilization.paymentwriteservice.domain.Mapper.toDto;

public class PaymentService {

    private final UserService userService;
    private final AccountRepository accountRepository;

    public PaymentService(UserService userService, AccountRepository accountRepository) {
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    public AccountModel createAccount() {
        Account account = buildAccount();
        AccountModel accountModel = Mapper.toDto(account);
        return accountRepository.save(accountModel);
    }

    private Account buildAccount() {
        return Account.Builder.anAccount().build();
    }

    public AccountModel charge(String keycloakId, int amount) {
        Account account = getAccountByKeycloakId(keycloakId);
        account.setMoney(getMoneyAfterCharge(amount, account));
        AccountModel updatedAccountModel = toDto(account);
        return accountRepository.save(updatedAccountModel);
    }

    private int getMoneyAfterCharge(int amount, Account account) {
        int newMoney = account.getMoney() - amount;
        if(newMoney < 0){
            throw new NotEnoughMoneyException(newMoney);
        }
        return newMoney;
    }

    private AccountModel getAccountById(long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    public AccountModel give(String keycloakId, int amount) {
        Account account = getAccountByKeycloakId(keycloakId);
        account.setMoney(getMoneyAfterGive(account, amount));
        AccountModel updatedAccountModel = toDto(account);
        return accountRepository.save(updatedAccountModel);
    }

    private Account getAccountByKeycloakId(String keycloakId) {
        UserDto userDto = userService.getUser(keycloakId);
        AccountModel accountModel = getAccountById(userDto.account());
        Account account = toDomain(accountModel);
        return account;
    }

    private int getMoneyAfterGive(Account account, int amount) {
        return account.getMoney() + amount;
    }
}
