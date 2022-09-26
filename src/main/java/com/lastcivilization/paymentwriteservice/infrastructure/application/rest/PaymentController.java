package com.lastcivilization.paymentwriteservice.infrastructure.application.rest;

import com.lastcivilization.paymentwriteservice.domain.PaymentService;
import com.lastcivilization.paymentwriteservice.domain.dto.AccountModel;
import com.lastcivilization.paymentwriteservice.infrastructure.application.rest.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lastcivilization.paymentwriteservice.infrastructure.application.rest.RestMapper.MAPPER;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    AccountDto createAccount(){
        AccountModel accountModel = paymentService.createAccount();
        return MAPPER.toDto(accountModel);
    }

    @PutMapping("/{keycloakId}/moneys/{amount}/charge")
    AccountDto chargeAccount(@PathVariable String keycloakId, @PathVariable int amount){
        AccountModel accountModel = paymentService.charge(keycloakId, amount);
        return MAPPER.toDto(accountModel);
    }

    @PutMapping("/{keycloakId}/moneys/{amount}/give")
    AccountDto giveToAccount(@PathVariable String keycloakId, @PathVariable int amount){
        AccountModel accountModel = paymentService.give(keycloakId, amount);
        return MAPPER.toDto(accountModel);
    }
}
