package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Product;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Shopping.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleOrder extends Order
{
    Map <Product, Integer> products = new HashMap<>();

    public SimpleOrder(double price, String customerName, String address)
    {
        super(price, customerName, address);
    }
    public void addProducts(Cart cart)
    {
        products.putAll(cart.products);
    }
    public String getProducts()
    {
        String result = "";
        for(Map.Entry<Product, Integer> entry : products.entrySet())
        {
            result += entry.getKey().getName();
            result += " x";
            result += entry.getValue().toString();
            result += " ";
        }
        return result;
    }
}
