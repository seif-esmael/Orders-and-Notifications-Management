package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;
import com.fci.advanced.se.OrdersandNotificationsManagement.Services.NotificationService;
import com.fci.advanced.se.OrdersandNotificationsManagement.Services.SimpleOrderService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.Notification;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationManager;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationTemplate;
import java.util.HashMap;

public class SimpleOrderObserver implements Observer
{
    private SimpleOrderService subject;
    private NotificationService notificationService = new NotificationService();
    public SimpleOrderObserver(SimpleOrderService subject)
    {
        this.subject = subject;
    }
    @Override
    public void update(int orderID, NotificationTemplate template)
    {
        NotificationManager.notifiedCustomer(subject.getOrder(orderID).getCustomerName());
        NotificationManager.usedTemplate(template);
        Notification notification = new Notification();
        notificationService.setNotification(notification);
        HashMap<Character,String> placeholders = new HashMap<>();
        placeholders.put('x', ((subject.getOrder(orderID))).getCustomerName());
        placeholders.put('y', ((SimpleOrder)(subject.getOrder(orderID))).getProducts());
        placeholders.put('z', String.valueOf((orderID)));
        notificationService.applyPlaceHolders(placeholders, template.getMessageContent());
        notificationService.addNotification(notification);
    }
}