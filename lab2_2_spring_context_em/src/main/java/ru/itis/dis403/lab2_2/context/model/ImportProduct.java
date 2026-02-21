package ru.itis.dis403.lab2_2.context.model;

public class ImportProduct {
    private Product product;
    private Integer count;
    private String supplier;

    public ImportProduct() {
    }

    public ImportProduct(Product product, Integer count, String supplier) {
        this.product = product;
        this.count = count;
        this.supplier = supplier;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
