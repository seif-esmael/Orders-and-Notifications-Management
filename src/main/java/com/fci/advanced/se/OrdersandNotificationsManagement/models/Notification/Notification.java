package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;

import java.util.Map;

public abstract class Notification {
    private int notificationID;
    private State state;
    private Map<Character,String>placeholders;

    public Notification(int notificationID, State state, Map<Character, String> placeholders) {
        this.notificationID = notificationID;
        this.state = state;
        this.placeholders = placeholders;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
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

    public void setPlaceholders(Order order) {
        this.placeholders = placeholders;
    }
}
