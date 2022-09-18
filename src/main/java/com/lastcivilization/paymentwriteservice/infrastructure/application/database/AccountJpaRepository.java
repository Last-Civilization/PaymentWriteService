package com.lastcivilization.paymentwriteservice.infrastructure.application.database;

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> { }
