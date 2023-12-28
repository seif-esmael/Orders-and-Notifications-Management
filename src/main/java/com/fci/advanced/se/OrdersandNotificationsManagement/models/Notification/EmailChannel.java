package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

public class EmailChannel implements Channel{
    private String email;
    @Override
    public String sendWithChannel(String email , String message) {
        return message+"\nThis Message was sent through the following E-mail:\n"+email;
    }
}
