package it.unibo.ddd.exercises.products;

import it.unibo.ddd.exercises.money.Money;

import java.math.BigDecimal;

public interface Product {
    ProductID getId();
    Money getPrice();
    void setPrice(Money newPrice);
    int getQuantity();
    void setQuantity(int quantity);
}
