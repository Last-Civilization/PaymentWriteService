package com.lastcivilization.paymentwriteservice.domain;

class Account {

    private Long id;
    private int money;

    Account(Long id, int money) {
        this.id = id;
        this.money = money;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    int getMoney() {
        return money;
    }

    void setMoney(int money) {
        this.money = money;
    }

    static final class Builder {

        private Long id;
        private int money = 0;

        private Builder() {
        }

        static Builder anAccount() {
            return new Builder();
        }

        Builder id(Long id) {
            this.id = id;
            return this;
        }

        Builder money(int money) {
            this.money = money;
            return this;
        }

        Account build() {
            return new Account(id, money);
        }
    }
}
