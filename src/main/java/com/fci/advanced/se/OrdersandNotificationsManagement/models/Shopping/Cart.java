package com.fci.advanced.se.OrdersandNotificationsManagement.models.Shopping;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    public Map<Product,Integer>products = new HashMap<>();
    private double totalPrice;

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
