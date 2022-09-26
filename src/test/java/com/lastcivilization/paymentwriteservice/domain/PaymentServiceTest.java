package com.lastcivilization.paymentwriteservice.domain;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountModel;
import com.lastcivilization.paymentwriteservice.domain.exception.NotEnoughMoneyException;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import com.lastcivilization.paymentwriteservice.domain.port.UserService;
import com.lastcivilization.paymentwriteservice.domain.port.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    private static final String KEYCLOAK_ID = "KEYCLOAK_ID";
    @InjectMocks
    private PaymentService underTest;

    @Mock
    private UserService userService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void shouldCreateAccount() {
        //given
        AccountModel expectedAccountModel = new AccountModel(
                null,
                0
        );
        Mockito.doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(accountRepository).save(any(AccountModel.class));
        //when
        AccountModel accountModel = underTest.createAccount();
        //then
        assertThat(accountModel).isEqualTo(expectedAccountModel);
    }

    private AccountModel getExpectedAccountDto() {
        return new AccountModel(
                0L,
                100
        );
    }

    @Test
    void shouldCharge() {
        //given
        AccountModel expectedAccountModel = getExpectedAccountDto();
        when(userService.getUser(anyString())).thenReturn(new UserDto(0L));
        when(accountRepository.findById(0L)).thenReturn(Optional.of(new AccountModel(
                0L,
                200
        )));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(accountRepository).save(any(AccountModel.class));
        //when
        AccountModel accountModel = underTest.charge(KEYCLOAK_ID, 100);
        //then
        assertThat(accountModel).isEqualTo(expectedAccountModel);
    }

    @Test
    void shouldThrowNotEnoughMoneyWhileCharging() {
        //given
        when(userService.getUser(anyString())).thenReturn(new UserDto(0L));
        when(accountRepository.findById(0L)).thenReturn(Optional.of(new AccountModel(
                0L,
                0
        )));
        //when
        Executable chargeExecutable = () -> underTest.charge(KEYCLOAK_ID, 100);
        //then
        assertThrows(NotEnoughMoneyException.class, chargeExecutable);
    }

    @Test
    void shouldGive() {
        //given
        AccountModel expectedAccountModel = getExpectedAccountDto();
        when(userService.getUser(anyString())).thenReturn(new UserDto(0L));
        when(accountRepository.findById(0L)).thenReturn(Optional.of(new AccountModel(
                0L,
                0
        )));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(accountRepository).save(any(AccountModel.class));
        //when
        AccountModel accountModel = underTest.give(KEYCLOAK_ID, 100);
        //then
        assertThat(accountModel).isEqualTo(expectedAccountModel);
    }
}