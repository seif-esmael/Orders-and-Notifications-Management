package com.fci.advanced.se.OrdersandNotificationsManagement.Services;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.OrderObserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompoundOrderService {
    private final List<Order> orders;
    private OrderObserver observer;

    public CompoundOrderService() {
        this.orders = new ArrayList<>();
    }

    public String showOrderDetails(int orderID)
    {
        return "Order Details";
    }

    /*public void notifyObserver()
    {
        this.observer.update(123);
    }*/
    public void addObserver(OrderObserver NO)
    {
        this.observer = NO;
    }

    public String placeOrder(int orderID)
    {
        //TODO
        return "Order Placed Successfully";
    }

    public String cancelOrderPlacement(int orderID)
    {
        //TODO
        return "Order Canceled";
    }

    public String packageOrder(String address,int orderID)
    {
        //TODO
        return "Order Packaged";
    }

    public String cancelOrderShipping(String address,int orderID)
    {
        //TODO
        return "Shipping Canceled";
    }

    public void addOrder(Order order)
    {
        orders.add(order);
    }
}
