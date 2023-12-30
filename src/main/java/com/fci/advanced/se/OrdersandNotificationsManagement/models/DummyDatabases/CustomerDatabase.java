package com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Shopping.Cart;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;

public interface CustomerDatabase
{
    void addCustomer(Customer customer);
    void removeCustomer(Customer customer);
    Customer getCustomer(String username);
    boolean isValid(String username);
    boolean findCustomer(String email, String password);
    Cart getUserCart(String userName);
    int getSize();
}
