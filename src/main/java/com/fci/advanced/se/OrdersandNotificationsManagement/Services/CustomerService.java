package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.CustomersDummyDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Shopping.Cart;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    public CustomerService()
    {
        if(CustomersDummyDatabase.getSize() == 0)
        {
            CustomersDummyDatabase.addCustomer(new Customer("Seif123", "123", "giza","seif@gmail.com","123456",3000.0));
            CustomersDummyDatabase.addCustomer(new Customer("Kiro123", "456", "haram","kiro@gmail.com","1234567",3500.0));
            CustomersDummyDatabase.addCustomer(new Customer("Yousef123", "789", "hadayek","saleboct@gmail.com","12345678",2000.0));
        }
    }
    public String register(Customer customer)
    {
        if(CustomersDummyDatabase.isValid(customer.getUserName()))
        {
            CustomersDummyDatabase.addCustomer(customer);
            return "Registrestion Completed Succsessfully";
        }
        return "User Name already exists!";
    }
    public String login(String email , String password)
    {
        if(CustomersDummyDatabase.findCustomer(email,password))
        {
            return "Login Successfully";
        }
        return "Invalid Email or Password!";
    }
}
