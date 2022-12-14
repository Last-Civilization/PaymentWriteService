package com.lastcivilization.paymentwriteservice.infrastructure.application.config;

import com.lastcivilization.paymentwriteservice.domain.PaymentService;
import com.lastcivilization.paymentwriteservice.domain.port.AccountRepository;
import com.lastcivilization.paymentwriteservice.domain.port.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.lastcivilization.paymentwriteservice.infrastructure.database")
@EnableJpaRepositories("com.lastcivilization.paymentwriteservice.infrastructure.database")
@ComponentScan("com.lastcivilization.paymentwriteservice")
class BeanConfiguration {

    @Bean
    PaymentService paymentService(AccountRepository accountRepository, UserService userService){
        return new PaymentService(userService, accountRepository);
    }
}
