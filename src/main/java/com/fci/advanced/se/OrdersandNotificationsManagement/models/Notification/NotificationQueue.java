package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationQueue
{
    public static List<Notification> notifications = new ArrayList<>();

    public static void insert(Notification notification)
    {
        notifications.add(notification);
    }
    public void remove(Notification notification)
    {
        notifications.remove(notification);
    }
}
