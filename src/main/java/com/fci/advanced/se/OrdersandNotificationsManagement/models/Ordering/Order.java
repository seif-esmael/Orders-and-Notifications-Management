package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

public abstract class Order
{
    protected static int idCounter = 1;
    protected int id;
    protected double price;
    protected String address;
    protected String customerName;
    protected boolean beingShipped = false;
    protected boolean placed = false;

    public Order(double price, String customerName, String address)
    {
        this.price = price;
        this.customerName = customerName;
        this.address = address;
        id=idCounter;
        idCounter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isBeingShipped() {
        return beingShipped;
    }

    public void setBeingShipped(boolean beingShipped) {
        this.beingShipped = beingShipped;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
}
