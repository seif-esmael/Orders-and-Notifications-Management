package com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.SimpleOrder;

import java.util.ArrayList;
import java.util.List;

public class OrdersDummyDatabase
{
    private static List<Order> orders = new ArrayList<>();
    public static void addOrder(Order order)
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
    public static Order getOrder(int orderID)
    {
        for(Order i : orders)
        {
            if(i.getId() == orderID)
            {
                return i;
            }
        }
        return null;
    }
    public static int getSize()
    {
        return orders.size();
    }
}
