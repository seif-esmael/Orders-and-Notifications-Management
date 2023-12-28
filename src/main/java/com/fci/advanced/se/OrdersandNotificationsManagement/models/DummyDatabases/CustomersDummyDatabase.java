package com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Shopping.Cart;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomersDummyDatabase
{
    private final static List<Customer> customers = new ArrayList<>();
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
    public void removeCustomer(Customer customer)
    {
        for(int i = 0; i < customers.size(); i++)
        {
            if(customers.get(i).equals(customer))
            {
                customers.remove(i);
                return;
            }
        }
    }
    public Customer getCustomer(String username)
    {
        for(Customer i : customers)
        {
            if(i.getUserName().equals(username))
            {
                return i;
            }
        }
        return null;
    }
    public boolean isValid(String username)
    {
        for(Customer i : customers)
        {
            if(i.getUserName().equals(username))
            {
                return false;
            }
        }
        return true;
    }
    public boolean findCustomer(String email, String password)
    {
        for (Customer c : customers) {
            if (c.getEmail().equals(email) && c.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
    public Cart getUserCart(String userName)
    {
        for (Customer c : customers)
        {
            if (c.getUserName().equals(userName))
            {
                return c.getCart();
            }
        }
        return null;
    }
}
