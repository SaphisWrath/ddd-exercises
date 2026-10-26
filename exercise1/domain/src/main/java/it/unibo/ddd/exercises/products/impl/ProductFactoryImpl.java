package it.unibo.ddd.exercises.products.impl;

import it.unibo.ddd.exercises.money.Money;
import it.unibo.ddd.exercises.products.Product;
import it.unibo.ddd.exercises.products.ProductFactory;
import it.unibo.ddd.exercises.products.ProductID;

public class ProductFactoryImpl implements ProductFactory {
    @Override
    public Product makeProduct(ProductID productID, Money price) {
        return makeProduct(productID, price, 0);
    }

    @Override
    public Product makeProduct(ProductID productID, Money price, int quantity) {
        return new ProductImpl(productID, price, quantity);
    }

    @Override
    public Product makeProduct(String name, String type, Money price, int quantity) {
        return new ProductImpl(makeProductID(name, type), price, quantity);
    }

    @Override
    public ProductID makeProductID(String name, String type) {
        return new ProductIDImpl(name, type);
    }
}
