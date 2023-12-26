package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

public class NotificationTemplate {
    private int templateID;
    private String messgaeContent;

    public NotificationTemplate(int templateID, String messgaeContent) {
        this.templateID = templateID;
        this.messgaeContent = messgaeContent;
    }

    public String useLanguage(Language language)
    {
        return "Zebby Tawil nek";
    }

    public int getTemplateID() {
        return templateID;
    }

    public void setTemplateID(int templateID) {
        this.templateID = templateID;
    }

    public String getMessgaeContent() {
        return messgaeContent;
    }

    public void setMessgaeContent(String messgaeContent) {
        this.messgaeContent = messgaeContent;
    }
}
