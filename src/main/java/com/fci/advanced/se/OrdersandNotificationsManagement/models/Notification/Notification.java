package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

import java.util.Map;

public class Notification
{
    private static Long notificationID=1L;
    private Map<Character,String>placeholders;
    private String messageContent;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    private Channel channel;

    public Notification()
    {
        notificationID++;
    }
    public void setContent(String content)
    {
        messageContent = content;
    }
    public String getContent()
    {
        return messageContent;
    }
    public Long getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Long notificationID) {
        Notification.notificationID = notificationID;
    }

    public Map<Character, String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(Map<Character, String> placeholders) {
        this.placeholders = placeholders;
    }
}
