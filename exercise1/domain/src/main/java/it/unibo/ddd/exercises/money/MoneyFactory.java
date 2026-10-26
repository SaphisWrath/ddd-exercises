package it.unibo.ddd.exercises.money;

import java.math.BigDecimal;
import java.time.Instant;

public interface MoneyFactory {
     Currency makeCurrencyEUR();
     Currency makeCurrencyUSD();
     Currency makeCurrencyGBP();

     Money makeMoney(BigDecimal amount, Currency currency);
     ExchangeRate makeRate(
         Currency from,
         Currency to,
         Instant time,
         BigDecimal amount
     );
}
