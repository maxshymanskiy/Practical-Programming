package com.theatre.app.model;

import static com.theatre.app.validation.ModelValidator.*;

import lombok.NonNull;

import java.time.LocalDateTime;

public record ShopTransaction(
        @NonNull String id,
        @NonNull Product product,
        int quantity,
        @NonNull TransactionType type,
        @NonNull LocalDateTime timestamp
) {
    public ShopTransaction {
        validateNotBlank(id, "Transaction ID");
        validatePositive(quantity, "Quantity");
    }
}