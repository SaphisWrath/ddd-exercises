package it.unibo.ddd.exercises.money.impl;

import it.unibo.ddd.exercises.money.Currency;
import it.unibo.ddd.exercises.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record MoneyImpl(BigDecimal amount, Currency currency) implements Money {
    public MoneyImpl {
        Objects.requireNonNull(currency);
        amount = amount.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public Money add(Money other) {
        if (!this.currency.equals(other.getCurrency())) {
            throw new IllegalArgumentException("Sum between money amounts in different currencies.");
        }
        return new MoneyImpl(this.amount.add(other.getAmount()), this.currency);
    }

    @Override
    public Money multiply(Money other) {
        if (!this.currency.equals(other.getCurrency())) {
            throw new IllegalArgumentException("Multiplication between money amounts in different currencies.");
        }
        return new MoneyImpl(this.amount.multiply(other.getAmount()), this.currency);
    }
}

