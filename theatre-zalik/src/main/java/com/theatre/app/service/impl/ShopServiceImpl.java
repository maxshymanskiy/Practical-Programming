package com.theatre.app.service.impl;

import com.theatre.app.model.Product;
import com.theatre.app.model.ShopTransaction;
import com.theatre.app.model.ShopTransaction.TransactionType;
import com.theatre.app.service.ShopService;
import com.theatre.app.validation.ShopValidator;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementation of shop service handling products and transactions.
 * Business validation is delegated to ShopValidator (Single Responsibility Principle).
 */
public class ShopServiceImpl implements ShopService {
    private final Map<String, Product> products = new HashMap<>();
    private final Map<String, Integer> inventory = new HashMap<>();
    private final List<ShopTransaction> transactions = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        products.put(product.id(), product);
        inventory.putIfAbsent(product.id(), 0);
    }

    @Override
    public void restockProduct(String productId, int quantity) {
        ShopValidator.requireProduct(products, productId);
        inventory.merge(productId, quantity, Integer::sum);
        recordTransaction(productId, quantity, TransactionType.BUY_IN);
    }

    @Override
    public void sellProduct(String productId, int quantity) {
        ShopValidator.requireProduct(products, productId);
        ShopValidator.validateStock(inventory, productId, quantity);

        inventory.merge(productId, -quantity, Integer::sum);
        recordTransaction(productId, quantity, TransactionType.SELL_OUT);
    }

    @Override
    public List<ShopTransaction> getTransactions() {
        return List.copyOf(transactions);
    }

    private void recordTransaction(String productId, int quantity, TransactionType type) {
        transactions.add(new ShopTransaction(
                UUID.randomUUID().toString(),
                products.get(productId),
                quantity,
                type,
                LocalDateTime.now()
        ));
    }
}

