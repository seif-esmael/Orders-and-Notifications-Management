package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.Channel;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.Notification;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationObserver;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;

import java.util.List;

public class NotificationService {
    private final List<Notification>notifications;

    public NotificationService(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String sendNotification(Channel channel, Order order)
    {
        return "Notification Sent!";
    }

    public void notifyObserver()
    {

    }
    public void addObserver(NotificationObserver NO)
    {

    }

    public void removeObserver(NotificationObserver NO)
    {

    }

    public String applyPlaceHolders()
    {
        return "Place Holders Applied";
    }
}
