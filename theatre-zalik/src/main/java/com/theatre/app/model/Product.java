package com.theatre.app.model;

import static com.theatre.app.validation.ModelValidator.*;

import lombok.NonNull;

import java.math.BigDecimal;

public record Product(
        @NonNull String id,
        @NonNull String name,
        @NonNull BigDecimal price
) {
    public Product {
        validateNotBlank(id, "Product ID");
        validateNotBlank(name, "Product name");
        validatePositive(price, "Product price");
    }
}