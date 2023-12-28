package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

public class Shipping
{
    private int orderID;
    private String address;
    private int cancelPlacementDuration;
    private boolean shipped = false;
    private double fees;

    public Shipping(int orderID, String address)
    {
        fees = Math.random()*(100.0);
        cancelPlacementDuration = 100;
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

    public boolean isBeingShipped() {
        return shipped;
    }

    public void setShipping(boolean status) {
        shipped = status;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}
