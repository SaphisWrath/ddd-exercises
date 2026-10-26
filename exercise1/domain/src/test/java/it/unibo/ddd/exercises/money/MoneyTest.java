package it.unibo.ddd.exercises.money;

import it.unibo.ddd.exercises.money.impl.CurrencyImpl;
import it.unibo.ddd.exercises.money.impl.MoneyFactoryImpl;
import it.unibo.ddd.exercises.money.impl.MoneyImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    final MoneyFactory moneyFactory = new MoneyFactoryImpl();

    private Money eur(String amount) {
        return moneyFactory.makeMoney(new BigDecimal(amount), moneyFactory.makeCurrencyEUR());
    }

    @Test
    void equalAmountsAreEqual() {
        String amount = "10.50";
        Money moneyEUR1 = eur("10.5");
        Money moneyEUR2 = eur(amount);
        Money moneyUSD = moneyFactory.makeMoney(
            new BigDecimal(amount),
            moneyFactory.makeCurrencyUSD()
        );
        assertEquals(moneyEUR1, moneyEUR2);
        assertNotEquals(moneyEUR1, moneyUSD);
    }

    @Test
    void operationsReturnNewValues() {
        String amount = "10";
        Money money1 = eur(amount);
        Money money2 = eur("2.5");
        assertEquals(eur("25"), money1.multiply(money2));
        assertEquals(eur("12.5"), money1.add(money2));
        assertEquals(eur(amount), money1);
    }

    @Test
    void cannotSumDifferentCurrencies() {
        assertThrows(IllegalArgumentException.class, () -> {
            Money money1 = eur("10.5");
            Money money2 = moneyFactory.makeMoney(new BigDecimal("10.5"), moneyFactory.makeCurrencyUSD());
            money1.add(money2);
        });
    }

    @Test
    void exchangeRatesConvertFromSourceToDestination() {
        ExchangeRate er = moneyFactory.makeRate(
            moneyFactory.makeCurrencyEUR(),
            moneyFactory.makeCurrencyUSD(),
            Instant.now(),
            new BigDecimal("1.10")
        );
        Money moneyEUR = eur("10.0");
        Money moneyUSD = moneyFactory.makeMoney(
            new BigDecimal("1.0"),
            moneyFactory.makeCurrencyUSD()
        );
        assertEquals(
            er.convert(moneyEUR),
            moneyFactory.makeMoney(new BigDecimal("11.0"), moneyFactory.makeCurrencyUSD())
        );
        assertThrows(IllegalArgumentException.class, () -> er.convert(moneyUSD));
    }
}
