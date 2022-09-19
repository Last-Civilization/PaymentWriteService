package com.lastcivilization.paymentwriteservice.domain.dto;

import java.util.Objects;

public class AccountDto {

    private Long id;
    private int money;

    public AccountDto(Long id, int money) {
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

        public static Builder anAccountDto() {
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

        public AccountDto build() {
            return new AccountDto(id, money);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return money == that.money && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, money);
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }
}
