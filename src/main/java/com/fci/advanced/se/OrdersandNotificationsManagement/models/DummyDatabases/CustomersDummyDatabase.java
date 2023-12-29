package com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Shopping.Cart;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomersDummyDatabase
{
    private final static List<Customer> customers = new ArrayList<>();
    public static void addCustomer(Customer customer)
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
    public static Customer getCustomer(String username)
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
    public static boolean isValid(String username)
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
    public static boolean findCustomer(String email, String password)
    {
        for (Customer c : customers) {
            if (c.getEmail().equals(email) && c.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
    public static Cart getUserCart(String userName)
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
    public static int getSize()
    {
        return customers.size();
    }
}
