package com.theatre.app.util.validation;

import static com.theatre.app.util.validation.ModelValidator.*;

import com.theatre.app.exception.InventoryException;
import com.theatre.app.model.Product;

import java.util.Map;

public final class ShopValidator {
    private ShopValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Product validateProductExists(Map<String, Product> products, String productId) {
        validateNotBlank(productId, "Product ID");
        Product product = products.get(productId);
        if (product == null) {
            throw new InventoryException("Product not found: " + productId);
        }
        return product;
    }

    public static void validateStock(Map<String, Integer> inventory, String productId, int quantity) {
        validatePositive(quantity, "Quantity");
        int currentStock = inventory.getOrDefault(productId, 0);
        if (currentStock < quantity) {
            throw new InventoryException(
                    "Not enough stock for product %s. Required: %d, Available: %d"
                            .formatted(productId, quantity, currentStock)
            );
        }
    }
}