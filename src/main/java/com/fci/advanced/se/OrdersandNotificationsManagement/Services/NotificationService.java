package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.*;
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
        NotificationQueue.insert(notification);
    }
    public String displayNotifications(Language language, Channel channel)
    {
        String result = "";
        for(Notification i : NotificationQueue.notifications)
        {
            result += i.getContent();
            result += useLanguage(language);
            result += useChannel(channel);
            result += '\n';
            result += '\n';
        }
        return result;
    }
}