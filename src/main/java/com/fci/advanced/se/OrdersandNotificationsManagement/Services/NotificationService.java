package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.CustomersDummyDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.*;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Product;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationService
{
    private Notification notification;

    public void setNotification(Notification notification)
    {
        this.notification = notification;
    }

    public void applyPlaceHolders(Map<Character, String> placeholders, String message)
    {
        String newMessage = "";
        for(int j = 0; j < message.length(); j++)
        {
            if(message.charAt(j) == '{')
            {
                j++;
                String temp = placeholders.get(message.charAt(j));
                placeholders.remove(message.charAt(j));
                newMessage += temp;
                j++;
            }
            else
            {
                newMessage += message.charAt(j);
            }
        }
        this.notification.setContent(newMessage);
    }
    public String useLanguage(Language language)
    {
        return " {THIS IS SENT USING " + language.toString() + " LANGUAGE}\n";
    }
    public String useChannel(Channel channel)
    {
        return channel.getMessageContent();
    }
    public void addNotification(Notification notification)
    {
        NotificationManager.insert(notification);
    }
    public String displayNotifications(Language language, Channel channel)
    {
        String result = "";
        for(Notification i : NotificationManager.notifications)
        {
            result += i.getContent();
            result += useLanguage(language);
            notification.setChannel(channel);
            result += useChannel(channel);
            result += '\n';
            result += '\n';
        }
        return result;
    }
    public String mostNotifiedEmail()
    {
        int maxi = 0;
        String username = "";
        for(Map.Entry<String, Integer> entry : NotificationManager.notifiedEmailCustomers.entrySet())
        {
            if(entry.getValue() > maxi)
            {
                maxi = entry.getValue();
                username = entry.getKey();
            }
        }
        if(!username.equals(""))
        {
            Customer customer = CustomersDummyDatabase.getCustomer(username);
            return "Most notified is: " + customer.getEmail() + " with total notifications: " + maxi;
        }
        return "No notifications sent yet!";
    }
    public String mostNotifiedPhoneNumber()
    {
        int maxi = 0;
        String username = "";
        for(Map.Entry<String, Integer> entry : NotificationManager.notifiedSMSCustomers.entrySet())
        {
            if(entry.getValue() > maxi)
            {
                maxi = entry.getValue();
                username = entry.getKey();
            }
        }
        if(!username.equals(""))
        {
            Customer customer = CustomersDummyDatabase.getCustomer(username);
            return "Most notified is: " + customer.getPhoneNumber() + " with total notifications: " + maxi;
        }
        return "No notifications sent yet!";
    }
    public String mostUsedTemplate()
    {
        int maxi = 0;
        NotificationTemplate notificationTemplate = NotificationTemplate.PlacingCompound;
        for(Map.Entry<NotificationTemplate, Integer> entry : NotificationManager.templateUsage.entrySet())
        {
            if(entry.getValue() > maxi)
            {
                maxi = entry.getValue();
                notificationTemplate = entry.getKey();
            }
        }
        if(maxi == 0)
        {
            return "No used template yet!";
        }
        return "Most used template is: " + notificationTemplate.getMessageContent();
    }
}