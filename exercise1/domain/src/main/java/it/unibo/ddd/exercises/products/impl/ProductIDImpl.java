package it.unibo.ddd.exercises.products.impl;

import it.unibo.ddd.exercises.products.ProductID;

import java.util.Objects;

public record ProductIDImpl(String name, String type) implements ProductID {
    public ProductIDImpl {
        Objects.requireNonNull(name, "name is null");
        Objects.requireNonNull(type, "type is null");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }
}
