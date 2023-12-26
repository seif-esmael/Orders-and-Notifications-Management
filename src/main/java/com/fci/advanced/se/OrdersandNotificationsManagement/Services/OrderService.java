package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final List<Order> orders;

    public OrderService() {
        this.orders = new ArrayList<>();
    }

    public String showOrderDetails(int orderID)
    {
        //TODO
        return "Order Deails";
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
