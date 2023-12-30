package com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Shipping;

public interface ShippingDatabase
{
    void addShipping(Shipping shipping);
    void removeShipping(Shipping shipping);
    Shipping getShipping(int orderID);
}
