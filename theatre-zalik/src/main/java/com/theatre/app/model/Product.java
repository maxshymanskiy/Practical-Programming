package com.theatre.app.model;

import static com.theatre.app.util.validation.ModelValidator.*;

public record Product(
        String id,
        String name,
        double price
) {
    public Product {
        validateNotBlank(id, "Product ID");
        validateNotBlank(name, "Product name");
        validatePositive(price, "Product price");
    }
}