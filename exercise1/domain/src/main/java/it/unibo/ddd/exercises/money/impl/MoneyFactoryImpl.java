package it.unibo.ddd.exercises.money.impl;

import it.unibo.ddd.exercises.money.Currency;
import it.unibo.ddd.exercises.money.ExchangeRate;
import it.unibo.ddd.exercises.money.Money;
import it.unibo.ddd.exercises.money.MoneyFactory;

import java.math.BigDecimal;
import java.time.Instant;

public class MoneyFactoryImpl implements MoneyFactory {

    @Override
    public Currency makeCurrencyEUR() {
        return new CurrencyImpl("euro", "€", "EUR");
    }

    @Override
    public Currency makeCurrencyUSD() {
        return new CurrencyImpl("dollar", "$", "USD");
    }

    @Override
    public Currency makeCurrencyGBP() {
        return new CurrencyImpl("pound", "£", "GBP");
    }

    @Override
    public Money makeMoney(BigDecimal amount, Currency currency) {
        return new MoneyImpl(amount, currency);
    }

    @Override
    public ExchangeRate makeRate(Currency from, Currency to, Instant time, BigDecimal amount) {
        return new ExchangeRateImpl(
            this,
            from,
            to,
            time,
            amount
        );
    }
}
