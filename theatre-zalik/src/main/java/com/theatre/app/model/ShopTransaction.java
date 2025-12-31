package com.theatre.app.model;

import static com.theatre.app.util.validation.ModelValidator.*;

import java.time.LocalDateTime;

public record ShopTransaction(
        String id,
        Product product,
        int quantity,
        TransactionType type,
        LocalDateTime timestamp
) {
    public ShopTransaction {
        validateNotBlank(id, "Transaction ID");
        validateNotNull(product, "Product");
        validatePositive(quantity, "Quantity");
        validateNotNull(type, "Transaction type");
        validateNotNull(timestamp, "Timestamp");
    }
}