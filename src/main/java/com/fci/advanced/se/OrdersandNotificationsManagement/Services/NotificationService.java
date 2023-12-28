package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.*;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {
    private final Notification notification;


    public NotificationService() {
        NotificationTemplate template1 = new NotificationTemplate("Dear {x} , your order of the {y} is confirmed. thanks for using our store :)");
        NotificationTemplate template2 = new NotificationTemplate("Dear {x} , your shipment process for your order of {y} has begun! :)");
        NotificationTemplate template3 = new NotificationTemplate("Dear {x} , your orderID for your order of {y} is {z} , keep it saved in case any issues occured. :)");
        NotificationTemplate template4 = new NotificationTemplate("Dear {x} , your shippingID for your order of {y} is {z} , keep it saved in case any issues occured. :)");
        this.notification = new Notification(template3);
        Map<Character, String> placeholders = new HashMap<>();
        placeholders.put('x', "kiro <3");
        placeholders.put('y', "potato");
        placeholders.put('z', "012");
        this.notification.setPlaceholders(placeholders);


    }

    public String sendNotification(Channel channel, String identifier)
    {
        this.notification.setState(State.SENT);

        return channel.sendWithChannel(identifier,this.applyPlaceHolders(notification.getPlaceholders()));
    }

    public String applyPlaceHolders(Map<Character, String> placeholders)
    {
        String message = this.notification.getTemplate().getMessageContent();
        int length = message.length();
        for (int i=0;i<placeholders.keySet().size();i++){
            for (int j = 0; j < length; j++) {
                if(placeholders.containsKey(message.charAt(j))&&message.charAt(j-1)=='{'){
                    String temp = placeholders.get(message.charAt(j));
                    placeholders.remove(message.charAt(j));
                    String Begin = message.substring(0,j-1);
                    String End = message.substring(j+2);
                    message = Begin + temp + End;
                }
            }

        }
        this.notification.getTemplate().setMessageContent(message);
        return message;
    }

    public String useLanguage(Language language)
    {
        return "{THIS IS SENT USING THE"+language.toString()+"LANGUAGE}\n"+this.notification.getTemplate().getMessageContent();
    }

}
