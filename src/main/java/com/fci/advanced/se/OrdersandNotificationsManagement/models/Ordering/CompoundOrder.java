package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

import java.util.List;

public class CompoundOrder extends Order{
    private List<Order> orders;

    public CompoundOrder(double price, String customerName, String address)
    {
        super(price, customerName, address);
    }
     public void addOrder(Order order)
     {

     }

     public boolean removeOrder(Order order)
     {
         return true;
     }
}
