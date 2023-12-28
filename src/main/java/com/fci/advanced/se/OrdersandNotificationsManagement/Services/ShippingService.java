package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Shipping;

import java.util.List;

public class ShippingService
{
    public String ship(int orderID)
    {
        return "Shipped";
    }

    public String cancelShipping(int orderID)
    {
        return "Shipping Canceled";
    }
}
