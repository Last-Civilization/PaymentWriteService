package com.lastcivilization.paymentwriteservice.infrastructure.application.rest;

import com.lastcivilization.paymentwriteservice.domain.dto.AccountDto;
import com.lastcivilization.paymentwriteservice.domain.port.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    AccountDto createAccount(){
        return paymentService.createAccount();
    }

    @PutMapping("/{keycloakId}/moneys/{amount}/charge")
    AccountDto chargeAccount(@PathVariable String keycloakId, @PathVariable int amount){
        return paymentService.charge(keycloakId, amount);
    }

    @PutMapping("/{keycloakId}/moneys/{amount}/give")
    AccountDto giveToAccount(@PathVariable String keycloakId, @PathVariable int amount){
        return paymentService.give(keycloakId, amount);
    }
}
