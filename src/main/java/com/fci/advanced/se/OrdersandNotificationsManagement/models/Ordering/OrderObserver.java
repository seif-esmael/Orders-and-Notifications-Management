package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

import com.fci.advanced.se.OrdersandNotificationsManagement.Services.OrderService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.Notification;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationQueue;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationTemplate;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.State;

import java.util.HashMap;
import java.util.Map;

public class OrderObserver implements Observer{
    private OrderService subject;
    @Override
    public void update(int orderID, NotificationTemplate template)
    {
        Notification notification = new Notification(template);
        HashMap<Character,String> placeholders = new HashMap<>();
//        subject.findOrderbyID(orderID).getId()
        //last here :D
//        notification.setPlaceholders();
    }
    public void addToQueue(Notification n)
    {

    }


    public OrderService getSubject() {
        return subject;
    }

    public void setSubject(OrderService subject) {
        this.subject = subject;
    }
}
