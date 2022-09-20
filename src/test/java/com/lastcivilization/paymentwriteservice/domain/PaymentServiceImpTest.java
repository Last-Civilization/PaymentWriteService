package com.lastcivilization.paymentwriteservice.domain;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;
import com.lastcivilization.paymentwriteservice.domain.dto.UserDto;
import com.lastcivilization.paymentwriteservice.domain.exception.NotEnoughMoneyException;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import com.lastcivilization.paymentwriteservice.domain.port.UserService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImpTest {

    private static final String KEYCLOAK_ID = "KEYCLOAK_ID";
    @InjectMocks
    private PaymentServiceImp underTest;

    @Mock
    private UserService userService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void shouldCreateAccount() {
        //given
        AccountDto expectedAccountDto = getExpectedAccountDto();
        expectedAccountDto.setMoney(0);
        Mockito.doAnswer(invocationOnMock -> {
            AccountDto result = (AccountDto) invocationOnMock.getArgument(0);
            result.setId(0L);
            return result;
        }).when(accountRepository).save(any(AccountDto.class));
        //when
        AccountDto accountDto = underTest.createAccount();
        //then
        assertThat(accountDto).isEqualTo(expectedAccountDto);
    }

    private AccountDto getExpectedAccountDto() {
        return AccountDto.Builder.anAccountDto()
                .id(0L)
                .money(100)
                .build();
    }

    @Test
    void shouldCharge() {
        //given
        AccountDto expectedAccountDto = getExpectedAccountDto();
        when(userService.getUser(anyString())).thenReturn(new UserDto(0L));
        when(accountRepository.findById(0L)).thenReturn(Optional.of(AccountDto.Builder.anAccountDto()
                .id(0L)
                .money(200)
                .build()));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(accountRepository).save(any(AccountDto.class));
        //when
        AccountDto accountDto = underTest.charge(KEYCLOAK_ID, 100);
        //then
        assertThat(accountDto).isEqualTo(expectedAccountDto);
    }

    @Test
    void shouldThrowNotEnoughMoneyWhileCharging() {
        //given
        when(userService.getUser(anyString())).thenReturn(new UserDto(0L));
        when(accountRepository.findById(0L)).thenReturn(Optional.of(AccountDto.Builder.anAccountDto()
                .id(0L)
                .money(0)
                .build()));
        //when
        Executable chargeExecutable = () -> underTest.charge(KEYCLOAK_ID, 100);
        //then
        assertThrows(NotEnoughMoneyException.class, chargeExecutable);
    }

    @Test
    void shouldGive() {
        //given
        AccountDto expectedAccountDto = getExpectedAccountDto();
        when(userService.getUser(anyString())).thenReturn(new UserDto(0L));
        when(accountRepository.findById(0L)).thenReturn(Optional.of(AccountDto.Builder.anAccountDto()
                .id(0L)
                .money(0)
                .build()));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(accountRepository).save(any(AccountDto.class));
        //when
        AccountDto accountDto = underTest.give(KEYCLOAK_ID, 100);
        //then
        assertThat(accountDto).isEqualTo(expectedAccountDto);
    }
}