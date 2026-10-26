package it.unibo.ddd.exercises.products;

import it.unibo.ddd.exercises.money.Money;

public interface ProductFactory {
    Product makeProduct(ProductID productID, Money price);
    Product makeProduct(ProductID productID, Money price, int quantity);
    Product makeProduct(String name, String type, Money price, int quantity);
    ProductID makeProductID(String name, String type);
}
