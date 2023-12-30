package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.InMemoryCustomersDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.CustomerDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    CustomerDatabase customersDatabase = new InMemoryCustomersDatabase();
    public CustomerService()
    {
        if(customersDatabase.getSize() == 0)
        {
            customersDatabase.addCustomer(new Customer("Seif123", "123", "giza","seif@gmail.com","123456",3000.0));
            customersDatabase.addCustomer(new Customer("Kiro123", "456", "haram","kiro@gmail.com","1234567",3500.0));
            customersDatabase.addCustomer(new Customer("Yousef123", "789", "hadayek","saleboct@gmail.com","12345678",2000.0));
        }
    }
    public String register(String username, String phoneNumber, String address, String email, String password, double balance)
    {
        if(customersDatabase.isValid(username))
        {
            customersDatabase.addCustomer(new Customer(username,phoneNumber,address,email,password,balance));
            return "Registrestion Completed Succsessfully";
        }
        return "User Name already exists!";
    }
    public String login(String email , String password)
    {
        if(customersDatabase.findCustomer(email,password))
        {
            return "Login Successfully";
        }
        return "Invalid Email or Password!";
    }
}
