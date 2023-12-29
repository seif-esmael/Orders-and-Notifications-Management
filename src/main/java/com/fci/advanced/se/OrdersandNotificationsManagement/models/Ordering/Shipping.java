package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

import java.util.Timer;

public class Shipping
{
    private int orderID;
    private String address;
    private int cancelPlacementDuration = 1;
    private double fees;

    public Shipping(int orderID, String address)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerForShipping(this), 60000);
        fees = Math.random()*(100.0);
        this.orderID = orderID;
        this.address = address;
    }
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getCancelPlacementDuration() {
        return cancelPlacementDuration;
    }

    public void setCancelPlacementDuration(int cancelPlacementDuration) {
        this.cancelPlacementDuration = cancelPlacementDuration;
    }
    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}
