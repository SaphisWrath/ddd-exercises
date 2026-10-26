package it.unibo.ddd.exercises.products.impl;

import it.unibo.ddd.exercises.products.Product;
import it.unibo.ddd.exercises.products.ProductID;
import it.unibo.ddd.exercises.products.Warehouse;

import java.util.*;

public class WarehouseImpl implements Warehouse {
    private final Map<ProductID, Product> inventory = new HashMap<>();

    @Override
    public Collection<Product> getInventory() {
        return Collections.unmodifiableCollection(inventory.values());
    }

    @Override
    public Optional<Product> findProductByID(ProductID id) {
        return Optional.ofNullable(inventory.get(id));
    }

    @Override
    public void store(Product product) {
        if (!inventory.containsKey(product.getId())) {
            inventory.put(product.getId(), product);
        }
    }

    @Override
    public void removeProduct(Product product) {
        removeProduct(product.getId());
    }

    @Override
    public void removeProduct(ProductID id) {
        inventory.remove(id);
    }
}
