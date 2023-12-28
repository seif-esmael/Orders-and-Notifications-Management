package com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Shipping;

import java.util.ArrayList;
import java.util.List;

public class ShippingsDummyDatabase
{
    private final List<Shipping> shippings = new ArrayList<>();
    public void addShipping(Shipping shipping)
    {
        shippings.add(shipping);
    }
    public void removeShipping(Shipping shipping)
    {
        for(int i = 0; i < shippings.size(); i++)
        {
            if(shippings.get(i).equals(shipping))
            {
                shippings.remove(i);
                return;
            }
        }
    }
    public Shipping getShipping(int orderID)
    {
        for(Shipping i : shippings)
        {
            if(i.getOrderID() == orderID)
            {
                return i;
            }
        }
        return null;
    }
}
