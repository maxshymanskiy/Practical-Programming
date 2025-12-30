package com.theatre.app.model;

import com.theatre.app.validation.ModelValidator;
import lombok.NonNull;

import java.time.LocalDateTime;

// NOTE: Record is perfect for immutable transaction log entries
public record ShopTransaction(
        @NonNull String id,
        @NonNull Product product,
        int quantity,
        @NonNull TransactionType type,
        @NonNull LocalDateTime timestamp
) {
    // Compact constructor delegates to centralized validation
    public ShopTransaction {
        ModelValidator.validatePositive(quantity, "Quantity");
    }

    public enum TransactionType {
        BUY_IN,    // Restocking
        SELL_OUT   // Selling to customer
    }
}

