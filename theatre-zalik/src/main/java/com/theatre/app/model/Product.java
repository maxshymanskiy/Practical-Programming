package com.theatre.app.model;

import com.theatre.app.validation.ModelValidator;
import lombok.NonNull;

import java.math.BigDecimal;

// NOTE: Record is perfect for immutable product data
public record Product(
        @NonNull String id,
        @NonNull String name,
        @NonNull BigDecimal price
) {
    // Compact constructor delegates to centralized validation
    public Product {
        ModelValidator.validateNotBlank(id, "Product ID");
        ModelValidator.validateNotBlank(name, "Product name");
        ModelValidator.validatePositive(price, "Product price");
    }
}

