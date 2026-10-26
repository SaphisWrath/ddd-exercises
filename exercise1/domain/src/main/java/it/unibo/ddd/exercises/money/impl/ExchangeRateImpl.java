package it.unibo.ddd.exercises.money.impl;

import it.unibo.ddd.exercises.money.Currency;
import it.unibo.ddd.exercises.money.ExchangeRate;
import it.unibo.ddd.exercises.money.Money;
import it.unibo.ddd.exercises.money.MoneyFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Objects;

public record ExchangeRateImpl(
    MoneyFactory moneyFactory,
    Currency from,
    Currency to,
    Instant time,
    BigDecimal rate
) implements ExchangeRate {
    public ExchangeRateImpl {
        Objects.requireNonNull(from, "from is null");
        Objects.requireNonNull(to, "to is null");
        Objects.requireNonNull(time, "time is null");
        rate = rate.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public Currency getSource() {
        return from;
    }

    @Override
    public Currency getDestination() {
        return to;
    }

    @Override
    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public Instant getWhen() {
        return time;
    }

    @Override
    public Money convert(Money money) {
        if (!money.getCurrency().equals(from)) {
            throw new IllegalArgumentException("Currency not supported for conversion.");
        }
        return this.moneyFactory.makeMoney(
            money.getAmount().multiply(this.rate),
            this.to
        );
    }
}
