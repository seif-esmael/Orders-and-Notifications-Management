package com.fci.advanced.se.OrdersandNotificationsManagement.Controllers;

import com.fci.advanced.se.OrdersandNotificationsManagement.Services.NotificationService;
import com.fci.advanced.se.OrdersandNotificationsManagement.Services.OrderService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.SMSChannel;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/get-order-details")
    @ResponseBody
    public Order showOrderDetails(){
        return this.orderService.showOrderDetails(1);
    }
}
