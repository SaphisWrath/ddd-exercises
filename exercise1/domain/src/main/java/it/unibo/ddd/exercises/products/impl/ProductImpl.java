package it.unibo.ddd.exercises.products.impl;

import it.unibo.ddd.exercises.money.Money;
import it.unibo.ddd.exercises.products.Product;
import it.unibo.ddd.exercises.products.ProductID;

import java.math.BigDecimal;

class ProductImpl implements Product {
    private final ProductID id;
    private Money price;
    private int quantity;
    ProductImpl(ProductID productID, Money price, int quantity) {
        this.id = productID;
        setPrice(price);
        setQuantity(quantity);
    }
    ProductImpl(ProductID productID, Money price) {
        this(productID, price, 0);
    }

    @Override
    public ProductID getId() {
        return id;
    }

    @Override
    public Money getPrice() {
        return price;
    }

    @Override
    public void setPrice(Money newPrice) {
        if(newPrice.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = newPrice;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ProductImpl && id.equals(((ProductImpl) o).id);
    }
}
