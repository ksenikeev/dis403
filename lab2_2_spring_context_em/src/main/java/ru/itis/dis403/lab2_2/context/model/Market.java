package ru.itis.dis403.lab2_2.context.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Market {
    private Map<Product, Integer> products = new HashMap<>();
    private List<Order> orders = new ArrayList<>();
    private List<ImportProduct> importProducts = new ArrayList<>();

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<ImportProduct> getImportProducts() {
        return importProducts;
    }
}
