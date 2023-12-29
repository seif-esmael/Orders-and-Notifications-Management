package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

import com.fci.advanced.se.OrdersandNotificationsManagement.Services.CompoundOrderService;
import com.fci.advanced.se.OrdersandNotificationsManagement.Services.NotificationService;
import com.fci.advanced.se.OrdersandNotificationsManagement.Services.SimpleOrderService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.Notification;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationTemplate;

import java.util.Comparator;
import java.util.HashMap;

public class CompoundOrderObserver implements Observer
{
    private CompoundOrderService subject;
    private NotificationService notificationService = new NotificationService();
    public CompoundOrderObserver(CompoundOrderService subject)
    {
        this.subject = subject;
    }
    @Override
    public void update(int orderID, NotificationTemplate template)
    {
        Notification notification = new Notification();
        notificationService.setNotification(notification);
        HashMap<Character,String> placeholders = new HashMap<>();
        placeholders.put('x', ((subject.getOrder(orderID))).getCustomerName());
        placeholders.put('y', subject.getOrderCustomers(orderID));
        placeholders.put('z', String.valueOf((orderID)));
        notificationService.applyPlaceHolders(placeholders, template.getMessageContent());
        notificationService.addNotification(notification);
    }
}
