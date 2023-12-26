package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Product;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Shopping.Cart;

import java.util.Map;

public class SimpleOrder extends Order{
    Map<Product,Integer>products;

    public SimpleOrder(double price, String customerName, String address) {
        super(price, customerName, address);
    }

    public void addProducts(Cart cart){
        products = cart.products;
    }
}
