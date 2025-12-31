package com.theatre.app.service.impl;

import static com.theatre.app.util.validation.ShopValidator.*;
import static com.theatre.app.util.validation.ModelValidator.*;

import com.theatre.app.model.Product;
import com.theatre.app.model.ShopTransaction;
import com.theatre.app.model.TransactionType;
import com.theatre.app.service.ShopService;

import java.time.LocalDateTime;
import java.util.*;

public class ShopServiceImpl implements ShopService {
    private final Map<String, Product> products = new HashMap<>();
    private final Map<String, Integer> inventory = new HashMap<>();
    private final List<ShopTransaction> transactions = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        validateNotNull(product, "Product");
        if (products.containsKey(product.id())) {
            throw new IllegalArgumentException("Product with ID " + product.id() + " already exists");
        }
        products.put(product.id(), product);
        inventory.putIfAbsent(product.id(), 0);
    }

    @Override
    public void restockProduct(String productId, int quantity) {
        validatePositive(quantity, "Restock quantity");
        validateProductExists(products, productId);

        int currentStock = inventory.getOrDefault(productId, 0);
        inventory.put(productId, currentStock + quantity);
        recordTransaction(productId, quantity, TransactionType.BUY_IN);
    }

    @Override
    public void sellProduct(String productId, int quantity) {
        validateProductExists(products, productId);
        validateStock(inventory, productId, quantity);

        int currentStock = inventory.get(productId);
        inventory.put(productId, currentStock - quantity);
        recordTransaction(productId, quantity, TransactionType.SELL_OUT);
    }

    @Override
    public List<ShopTransaction> getTransactions() {
        return List.copyOf(transactions);
    }

    @Override
    public int getStock(String productId) {
        validateProductExists(products, productId);
        return inventory.getOrDefault(productId, 0);
    }

    private void recordTransaction(String productId, int quantity, TransactionType type) {
        String transactionId = generateTransactionId(productId, type);
        transactions.add(
                new ShopTransaction(
                        transactionId,
                        products.get(productId),
                        quantity,
                        type,
                        LocalDateTime.now()
                )
        );
    }

    private String generateTransactionId(String productId, TransactionType type) {
        return "TR-" + productId + "-" + type + "-" + System.currentTimeMillis();
    }
}

