package com.lastcivilization.paymentwriteservice.domain;

class Account {

    private Long id;
    private int money;

    public Account(Long id, int money) {
        this.id = id;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static final class Builder {

        private Long id;
        private int money;

        private Builder() {
        }

        public static Builder anAccount() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder money(int money) {
            this.money = money;
            return this;
        }

        public Account build() {
            return new Account(id, money);
        }
    }
}
