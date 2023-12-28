package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

public class SMSChannel implements Channel{
    private String phoneNumber;
    @Override
    public String sendWithChannel(String phoneNumber, String message)
    {
        return message+"\nThis Message was sent through the following Phone Numeber:\n"+phoneNumber;
    }
}
