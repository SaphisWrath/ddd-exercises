package it.unibo.ddd.exercises.money;

import java.math.BigDecimal;
import java.time.Instant;

public interface ExchangeRate {
    Currency getSource();

    Currency getDestination();

    BigDecimal getRate();

    Instant getWhen();

    Money convert(Money money);
}
