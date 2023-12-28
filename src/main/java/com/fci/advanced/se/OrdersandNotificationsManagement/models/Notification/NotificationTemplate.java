package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

public class NotificationTemplate {
    private static int templateID=1;
    private String messageContent;

    public NotificationTemplate(String messageContent) {
        templateID++;
        this.messageContent = messageContent;
    }

    public int getTemplateID() {
        return templateID;
    }

    public void setTemplateID(int templateID) {
        this.templateID = templateID;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
