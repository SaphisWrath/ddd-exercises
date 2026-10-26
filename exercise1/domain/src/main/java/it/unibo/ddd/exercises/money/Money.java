package it.unibo.ddd.exercises.money;

import java.math.BigDecimal;

public interface Money {

    /**
     * @return the currency of the money
     */
    Currency getCurrency();

    /**
     * @return the amount of money
     */
    BigDecimal getAmount();

    /**
     * @param other instance of Money
     * @return the sum of this instance and other
     */
    Money add(Money other);

    /**
     * @param other instance of Money
     * @return the multiplication of this instance and other
     */
    Money multiply(Money other);
}
