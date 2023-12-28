package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;

import java.util.Map;

public class Notification {

    protected static Long notificationID=1L;
    protected State state;
    protected Map<Character,String>placeholders;


    public NotificationTemplate getTemplate() {
        return template;
    }

    public void setTemplate(NotificationTemplate template) {
        this.template = template;
    }

    protected NotificationTemplate template;

    public Notification(NotificationTemplate template) {
        notificationID++;
        this.state = State.TO_BE_SENT;
        this.template = template;
    }

    public Long getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Long notificationID) {
        Notification.notificationID = notificationID;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Map<Character, String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(Map<Character, String> placeholders) {
        this.placeholders = placeholders;
    }
}
