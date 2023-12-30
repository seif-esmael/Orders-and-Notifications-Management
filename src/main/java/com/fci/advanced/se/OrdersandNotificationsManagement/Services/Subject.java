package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationTemplate;

public interface Subject
{
    void notifyObserver(int orderID, NotificationTemplate template);
}
