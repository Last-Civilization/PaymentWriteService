package com.lastcivilization.paymentwriteservice.infrastructure.application.rest;

import com.lastcivilization.paymentwriteservice.domain.PaymentService;
import com.lastcivilization.paymentwriteservice.domain.view.AccountModel;
import com.lastcivilization.paymentwriteservice.infrastructure.application.rest.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

import static com.lastcivilization.paymentwriteservice.infrastructure.application.rest.RestMapper.MAPPER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Validated
class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(CREATED)
    AccountDto createAccount(){
        AccountModel accountModel = paymentService.createAccount();
        return MAPPER.toDto(accountModel);
    }

    @PutMapping("/{keycloakId}/moneys/{amount}/charge")
    AccountDto chargeAccount(@PathVariable String keycloakId, @PathVariable @Min(1) int amount){
        AccountModel accountModel = paymentService.charge(keycloakId, amount);
        return MAPPER.toDto(accountModel);
    }

    @PutMapping("/{keycloakId}/moneys/{amount}/give")
    AccountDto giveToAccount(@PathVariable String keycloakId, @PathVariable @Min(1) int amount){
        AccountModel accountModel = paymentService.give(keycloakId, amount);
        return MAPPER.toDto(accountModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void deleteAccount(@PathVariable @Min(1) long id){
        paymentService.deleteAccount(id);
    }
}
