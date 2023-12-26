package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

public class Shipping {

    private int orderID;
    private String address;
    private String Date;
    private int cancelPlacementDuration;
    private boolean status;
    private double fees;

    public Shipping(int orderID, String address, String date, int cancelPlacementDuration, boolean status, double fees) {
        this.orderID = orderID;
        this.address = address;
        Date = date;
        this.cancelPlacementDuration = cancelPlacementDuration;
        this.status = status;
        this.fees = fees;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getCancelPlacementDuration() {
        return cancelPlacementDuration;
    }

    public void setCancelPlacementDuration(int cancelPlacementDuration) {
        this.cancelPlacementDuration = cancelPlacementDuration;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}
