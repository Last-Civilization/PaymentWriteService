package com.lastcivilization.paymentwriteservice.infrastructure.application.config;

import com.lastcivilization.paymentwriteservice.domain.AccountServiceImp;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import com.lastcivilization.paymentwriteservice.domain.port.AccountService;
import com.lastcivilization.paymentwriteservice.domain.port.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@Configuration
@EntityScan("com.lastcivilization.paymentwriteservice")
@EnableJpaRepositories("com.lastcivilization.paymentwriteservice")
@ComponentScan("com.lastcivilization.paymentwriteservice")
class BeanConfiguration {

    @Bean
    AccountService accountService(AccountRepository accountRepository, UserService userService){
        return new AccountServiceImp(userService, accountRepository);
    }
}
