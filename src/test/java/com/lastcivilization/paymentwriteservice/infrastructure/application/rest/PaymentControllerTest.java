package com.lastcivilization.paymentwriteservice.infrastructure.application.rest;

import com.lastcivilization.paymentwriteservice.utils.IntegrationBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PaymentControllerTest extends IntegrationBaseClass {

    @Test
    void shouldCreateAccount() throws Exception {
        //given
        //when
        ResultActions createResult = mockMvc.perform(post("/payments"));
        //then
        createResult.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.money").value(0));
    }

    @Test
    void shouldChargeAccount() throws Exception {
        //given
        accountCreator.setTestAccountMoney(100);
        //when
        ResultActions chargeResult = mockMvc.perform(put("/payments/1/moneys/100/charge"));
        //then
        chargeResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.money").value(0));
    }

    @Test
    void shouldReturnValidationExceptionStatusWhileChargingAccount() throws Exception {
        //given
        //when
        ResultActions chargeResult = mockMvc.perform(put("/payments/1/moneys/0/charge"));
        //then
        chargeResult.andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnAccountNotFoundStatusWhileChargingAccount() throws Exception {
        //given
        //when
        ResultActions chargeResult = mockMvc.perform(put("/payments/3/moneys/100/charge"));
        //then
        chargeResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileChargingAccount() throws Exception {
        //given
        //when
        ResultActions chargeResult = mockMvc.perform(put("/payments/2/moneys/100/charge"));
        //then
        chargeResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotEnoughMoneyStatusWhileChargingAccount() throws Exception {
        //given
        accountCreator.setTestAccountMoney(0);
        //when
        ResultActions chargeResult = mockMvc.perform(put("/payments/1/moneys/100/charge"));
        //then
        chargeResult.andExpect(status().isBadRequest());
    }

    @Test
    void shouldGiveToAccount() throws Exception {
        //given
        accountCreator.setTestAccountMoney(0);
        //when
        ResultActions giveResult = mockMvc.perform(put("/payments/1/moneys/100/give"));
        //then
        giveResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.money").value(100));
    }

    @Test
    void shouldReturnValidationExceptionStatusWhileGivingToAccount() throws Exception {
        //given
        //when
        ResultActions chargeResult = mockMvc.perform(put("/payments/1/moneys/0/give"));
        //then
        chargeResult.andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnAccountNotFoundStatusWhileGivingToAccount() throws Exception {
        //given
        //when
        ResultActions giveResult = mockMvc.perform(put("/payments/3/moneys/100/give"));
        //then
        giveResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileGivingToAccount() throws Exception {
        //given
        //when
        ResultActions giveResult = mockMvc.perform(put("/payments/2/moneys/100/give"));
        //then
        giveResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAccount() throws Exception {
        //given
        accountCreator.setTestAccountMoney(0);
        //when
        ResultActions deleteResult = mockMvc.perform(delete("/payments/1"));
        //then
        deleteResult.andExpect(status().isNoContent());
    }
}