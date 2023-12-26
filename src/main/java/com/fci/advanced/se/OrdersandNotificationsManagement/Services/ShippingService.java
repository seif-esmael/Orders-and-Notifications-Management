package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Shipping;

import java.util.List;

public class ShippingService {
    private final List<Shipping> shippings;

    public ShippingService(List<Shipping> shippings) {
        this.shippings = shippings;
    }

    public String ship(int orderID)
    {
        return "Shipped";
    }

    public String cancelShipping(int orderID)
    {
        return "Shipping Canceled";
    }
}
