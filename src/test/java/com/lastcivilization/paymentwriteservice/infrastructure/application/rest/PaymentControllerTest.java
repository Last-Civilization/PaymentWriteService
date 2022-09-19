package com.lastcivilization.paymentwriteservice.infrastructure.application.rest;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;
import com.lastcivilization.paymentwriteservice.utils.IntegrationBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PaymentControllerTest extends IntegrationBaseClass {

    @Test
    void shouldCreateAccount() throws Exception {
        //given
        //when
        ResultActions createResult = mockMvc.perform(post("/payments"));
        //then
        createResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.money").value(0));
    }

    @Test
    void shouldChargeAccount() throws Exception {
        //given
        accountCreator.setTestAccountMoney(100);
        //when
        ResultActions chargeResult = mockMvc.perform(patch("/payments/1/moneys/100/charge"));
        //then
        chargeResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.money").value(0));
    }

    @Test
    void shouldReturnAccountNotFoundStatusWhileChargingAccount() throws Exception {
        //given
        //when
        ResultActions chargeResult = mockMvc.perform(patch("/payments/3/moneys/100/charge"));
        //then
        chargeResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileChargingAccount() throws Exception {
        //given
        //when
        ResultActions chargeResult = mockMvc.perform(patch("/payments/2/moneys/100/charge"));
        //then
        chargeResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotEnoughMoneyStatusWhileChargingAccount() throws Exception {
        //given
        accountCreator.setTestAccountMoney(0);
        //when
        ResultActions chargeResult = mockMvc.perform(patch("/payments/1/moneys/100/charge"));
        //then
        chargeResult.andExpect(status().isBadRequest());
    }

    @Test
    void shouldGiveToAccount() throws Exception {
        //given
        accountCreator.setTestAccountMoney(0);
        //when
        ResultActions giveResult = mockMvc.perform(patch("/payments/1/moneys/100/give"));
        //then
        giveResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.money").value(100));
    }

    @Test
    void shouldReturnAccountNotFoundStatusWhileGivingToAccount() throws Exception {
        //given
        //when
        ResultActions giveResult = mockMvc.perform(patch("/payments/3/moneys/100/give"));
        //then
        giveResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileGivingToAccount() throws Exception {
        //given
        //when
        ResultActions giveResult = mockMvc.perform(patch("/payments/2/moneys/100/give"));
        //then
        giveResult.andExpect(status().isNotFound());
    }
}