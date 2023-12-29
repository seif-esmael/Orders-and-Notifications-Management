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
        for(int i=0; i<placeholders.keySet().size(); i++)
        {
            for(int j = 0; j < message.length(); j++)
            {
                if(placeholders.containsKey(message.charAt(j)) && message.charAt(j-1)=='{')
                {
                    String temp = placeholders.get(message.charAt(j));
                    placeholders.remove(message.charAt(j));
                    String Begin = message.substring(0,j-1);
                    String End = message.substring(j+2);
                    message = Begin + temp + End;
                    this.notification.setContent(message);
                }
            }
        }
    }
    public String useLanguage(Language language)
    {
        return " {THIS IS SENT USING " + language.toString() + "LANGUAGE}\n";
    }
    public void addNotification(Notification notification)
    {
        NotificationQueue.insert(notification);
    }
    public String displayNotifications(Language language)
    {
        String result = "sxsxs";
        for(Notification i : NotificationQueue.notifications)
        {
            result += i.getContent();
            result += useLanguage(language);
            result += '\n';
        }
        return result;
    }
}