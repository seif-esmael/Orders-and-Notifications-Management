package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

public enum Channel
{
    Email("This Message was sent through Email channel"),
    SMS("This Message was sent through SMS channel");
    private String value;

    Channel(String type)
    {
        this.value = type;
    }
    public String getMessageContent()
    {
        return value;
    }
}
