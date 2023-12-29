package com.fci.advanced.se.OrdersandNotificationsManagement.Controllers;

import com.fci.advanced.se.OrdersandNotificationsManagement.Services.CustomerService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController
{
    private CustomerService customerservice = new CustomerService();
    @PostMapping(value = "/register")
    public String register(@RequestParam String username, @RequestParam String phoneNumber, @RequestParam String address, @RequestParam String email, @RequestParam String password, @RequestParam double balance)
    {
        return customerservice.register(username,phoneNumber,address,email,password,balance);
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam String email, @RequestParam String password)
    {
        return customerservice.login(email, password);
    }
}
