package ru.itis.dis403.lab2_2.context.model;

public class Order {
    private Product product;
    private Integer count;
    private String client;

    public Order() {
    }

    public Order(Product product, Integer count, String client) {
        this.product = product;
        this.count = count;
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
