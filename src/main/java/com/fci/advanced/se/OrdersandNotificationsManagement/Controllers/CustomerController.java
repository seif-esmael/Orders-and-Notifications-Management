package com.fci.advanced.se.OrdersandNotificationsManagement.Controllers;

import com.fci.advanced.se.OrdersandNotificationsManagement.Services.CustomerService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private CustomerService customerservice;

    public CustomerController(CustomerService customerservice) {
        this.customerservice = customerservice;
    }

    @PostMapping(value = "/register")
    public String register(@RequestBody Customer customer)
    {
        return customerservice.register(customer);
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam String email, @RequestParam String password)
    {
        return customerservice.login(email, password);
    }
}
