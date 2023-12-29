package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationTemplate;

public interface Observer
{
    void update(int orderID, NotificationTemplate template);
}
