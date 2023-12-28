package com.fci.advanced.se.OrdersandNotificationsManagement.Services;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationTemplate;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.OrderObserver;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.SimpleOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final List<Order> orders;
    private OrderObserver observer;

    public OrderService() {
        this.orders = new ArrayList<>();
        observer = new OrderObserver();
        observer.setSubject(this);
        this.orders.add(new SimpleOrder(50,"yousef","hadayek-elahram"));
        this.orders.add(new SimpleOrder(100,"mo","dokki"));
        this.orders.add(new SimpleOrder(520,"ahmed","ismailia"));
        this.orders.add(new SimpleOrder(90,"ayman","giza"));
    }

    public Order findOrderbyID(int orderID){
        for(Order i: orders){
            if(orderID == i.getId()){
                return i;
            }
        }
        return null;
    }

//    public Order showOrderDetails(int orderID)
//    {
//        for(Order i: orders){
//            if(orderID == i.getId()){
//                return i;
//            }
//        }
//        return "Order not found!";
//    }

    public void notifyObserver(int orderID,NotificationTemplate template)
    {
        this.observer.update(orderID,template);
    }
    public void addObserver(OrderObserver NO)
    {
        this.observer = NO;
    }

    public String placeOrder(int orderID)
    {
        if(findOrderbyID(orderID)!=null)
        {
            if(!findOrderbyID(orderID).isPlaced()) {
                findOrderbyID(orderID).setPlaced(true);
                notifyObserver(orderID);
            }
            else
                return "Order Already Placed";
        }
        else {
            return "Order not found!";
        }
        return "Order Placed Successfully";
    }

    public String cancelOrderPlacement(int orderID)
    {
        //TODO
        return "Order Canceled";
    }

    public String packageOrder(String address,int orderID)
    {
        if(findOrderbyID(orderID)!=null)
        {
            if(!findOrderbyID(orderID).isBeingShipped()) {
                findOrderbyID(orderID).setBeingShipped(true);
                NotificationTemplate template = new NotificationTemplate("Dear {x} , your shipment process for your order of {y} has begun! :)");
                notifyObserver(orderID,template);
            }
            else
                return "Order Already Placed";
        }
        else {
            return "Order not found!";
        }
        return "Order Packaged!";
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
