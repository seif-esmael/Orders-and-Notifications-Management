package com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.SimpleOrder;

import java.util.ArrayList;
import java.util.List;

public class OrdersDummyDatabase
{
    private static List<Order> orders = new ArrayList<>();

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
    public Order getOrder(int orderID)
    {
        for(Order i : orders)
        {
            return i;
        }
        return null;
    }
}
