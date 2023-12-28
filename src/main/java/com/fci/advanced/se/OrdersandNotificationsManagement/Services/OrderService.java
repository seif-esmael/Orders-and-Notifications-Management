package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;

public interface OrderService
{
    String placeOrder(int orderID);

    String cancelOrderPlacement(int orderID);

    String packageOrder(String address,int orderID);
    String cancelOrderShipping(String address,int orderID);
    Order showOrderDetails(int orderID);
}
