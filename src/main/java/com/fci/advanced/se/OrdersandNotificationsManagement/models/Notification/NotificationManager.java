package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;

import java.util.*;

public class NotificationManager
{
    public static List<Notification> notifications = new ArrayList<>();
    public static Map<NotificationTemplate, Integer> templateUsage = new HashMap<>();
    public static Map<String, Integer> notifiedCustomers = new HashMap<>();
    public static Map<String, Integer> notifiedEmailCustomers = new HashMap<>();
    public static Map<String, Integer> notifiedSMSCustomers = new HashMap<>();

    public static void notifiedCustomer(String username)
    {
        int old = 0;
        if(notifiedCustomers.containsKey(username))
        {
            old = notifiedCustomers.get(username);
            notifiedCustomers.remove(username);
        }
        notifiedCustomers.put(username, old + 1);
    }
    public static void usedTemplate(NotificationTemplate notificationTemplate)
    {
        int old = 0;
        if(templateUsage.containsKey(notificationTemplate))
        {
            old = templateUsage.get(notificationTemplate);
            templateUsage.remove(notificationTemplate);
        }
        templateUsage.put(notificationTemplate, old + 1);
    }
    public static void insert(Notification notification)
    {

        Timer timer = new Timer();
        timer.schedule(new TimerForNotification(), 60000);
        notifications.add(notification);

        Channel channel = notification.getChannel();
        String username = notification.getPlaceholders().get('x');
        if(channel == Channel.SMS){
            int old = 0;
            if(notifiedSMSCustomers.containsKey(username))
            {
                old = notifiedSMSCustomers.get(username);
                notifiedSMSCustomers.remove(username);
            }
            notifiedSMSCustomers.put(username, old + 1);

        }
        else if(channel == Channel.Email){
            int old = 0;
            if(notifiedEmailCustomers.containsKey(username))
            {
                old = notifiedEmailCustomers.get(username);
                notifiedEmailCustomers.remove(username);
            }
            notifiedEmailCustomers.put(username, old + 1);
        }
    }
    public void remove(Notification notification)
    {
        notifications.remove(notification);
    }
    public static void pop()
    {
        if(!notifications.isEmpty())
        {
            notifications.remove(0);
        }
    }
}
