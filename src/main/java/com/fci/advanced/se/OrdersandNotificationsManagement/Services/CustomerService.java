package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Shopping.Cart;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final List<Customer> customers;

    public CustomerService(List<Customer> customers) {
        this.customers = customers;
    }

    public CustomerService() {
        this.customers = new ArrayList<>();
        customers.add(new Customer("Seif123", "123", "giza","seif@gmail.com","123456",3000.0));
        customers.add(new Customer("Kiro123", "456", "haram","kiro@gmail.com","1234567",3500.0));
        customers.add(new Customer("Yousef123", "789", "hadayek","saleboct@gmail.com","12345678",2000.0));
    }

    public String register(Customer customer)
    {
        for (Customer c : customers) {
            if (c.getUserName().equals( customer.getUserName()))
            {
                return "User Name already exists!";
            }
        }
        customers.add(customer);
        return "Registrestion Completed Succsessfully";
    }

    public String login(String email , String password)
    {
        for (Customer c : customers) {
            if (c.getEmail().equals(email) && c.getPassword().equals(password))
            {
                return "Login Successfully";
            }
        }
        return "Invalid Email or Password!";
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
