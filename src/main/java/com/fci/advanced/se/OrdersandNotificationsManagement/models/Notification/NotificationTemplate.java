package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

public enum NotificationTemplate
{
    PlacingSimple("Dear {x} , your orderID for your order of {y} is {z} , keep it saved in case any issues occured. :)"),
    PlacingCompound("Dear {x} , your orderID for your order with {y} is {z} , keep it saved in case any issues occured. :)"),
    ShippingSimple("Dear {x} , your shipment process for your order of {y} has begun! :)"),
    ShippingCompound("Dear {x} , your shipment process for your order with {y} has begun! :)");
    private String value;

    NotificationTemplate(String type)
    {
        this.value = type;
    }
    public String getMessageContent()
    {
        return value;
    }
}
