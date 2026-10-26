package it.unibo.ddd.exercises.products;

import java.util.Collection;
import java.util.Optional;

public interface Warehouse {
    Collection<Product> getInventory();
    Optional<Product> findProductByID(ProductID id);
    void store(Product product);
    void removeProduct(Product product);
    void removeProduct(ProductID id);
}
