package com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;

public interface OrderDatabase
{
     void addOrder(Order order);
     void removeOrder(Order order);
     Order getOrder(int orderID);
}
