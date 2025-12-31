package com.theatre.app.model;

import static com.theatre.app.util.validation.ModelValidator.*;

import lombok.NonNull;

public record Product(
        @NonNull String id,
        @NonNull String name,
        double price
) {
    public Product {
        validateNotBlank(id, "Product ID");
        validateNotBlank(name, "Product name");
        validatePositive(price, "Product price");
    }
}