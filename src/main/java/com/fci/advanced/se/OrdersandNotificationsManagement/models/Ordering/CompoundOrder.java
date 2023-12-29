package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order
{
    private List<Order> orders = new ArrayList<>();

    public CompoundOrder(double price, String customerName, String address)
    {
        super(price, customerName, address);
    }
     public void addOrder(Order order)
     {
         orders.add(order);
     }
     public void removeOrder(Order order)
     {
         for(int i = 0; i < orders.size(); i++)
         {
             if(orders.get(i).equals(order))
             {
                 orders.remove(i);
                 return;
             }
         }
     }
     public List<Order> getOrders()
     {
         return orders;
     }
}
