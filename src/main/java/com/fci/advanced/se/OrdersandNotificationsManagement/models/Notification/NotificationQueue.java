package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

import java.util.List;

public class NotificationQueue {
    public static List<Notification> notifications;

    public NotificationQueue(List<Notification> notifications) {
        this.notifications = notifications;
    }
    public void insert(Notification n)
    {
        this.notifications.add(n);
    }
    public void remove(Notification n)
    {
        this.notifications.remove(n);

    }
    public String display()
    {
        String s = notifications.toString();
        return s;
    }
}
