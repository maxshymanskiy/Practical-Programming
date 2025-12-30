package com.theatre.app.service;

import com.theatre.app.model.Product;
import com.theatre.app.model.ShopTransaction;

import java.util.List;

public interface ShopService {

    void addProduct(Product product);

    void restockProduct(String productId, int quantity);

    void sellProduct(String productId, int quantity);

    List<ShopTransaction> getTransactions();
}

