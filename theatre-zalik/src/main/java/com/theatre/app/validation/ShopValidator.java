package com.theatre.app.validation;

import com.theatre.app.exception.InventoryException;
import com.theatre.app.model.Product;

import java.util.Map;
import java.util.Optional;

/**
 * Validator for shop-related business rules.
 * Separates validation logic from service implementation (Single Responsibility Principle).
 */
public final class ShopValidator {

    private ShopValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Finds product or throws exception if not found.
     */
    public static Product requireProduct(Map<String, Product> products, String productId) {
        return Optional.ofNullable(products.get(productId))
                .orElseThrow(() -> new InventoryException("Product not found: " + productId));
    }

    /**
     * Validates sufficient stock for sell operation.
     */
    public static void validateStock(Map<String, Integer> inventory, String productId, int quantity) {
        int currentStock = inventory.getOrDefault(productId, 0);
        if (currentStock < quantity) {
            throw new InventoryException(
                    "Not enough stock for product %s. Required: %d, Available: %d"
                            .formatted(productId, quantity, currentStock)
            );
        }
    }
}

