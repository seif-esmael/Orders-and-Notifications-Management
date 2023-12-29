package com.fci.advanced.se.OrdersandNotificationsManagement.Controllers;

import com.fci.advanced.se.OrdersandNotificationsManagement.Services.SimpleOrderService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class SimpleOrderController
{
    private SimpleOrderService orderService = new SimpleOrderService();

    @GetMapping(value = "/showOrderDetails")
    public Order showOrderDetails(@RequestParam int orderID)
    {
        return orderService.showOrderDetails(orderID);
    }
    @PutMapping(value = "/placeOrder")
    public String placeOrder(@RequestParam int orderID)
    {
        return orderService.placeOrder(orderID);
    }
    @PutMapping(value = "/cancelOrderPlacement")
    public String cancelOrderPlacement(@RequestParam int orderID)
    {
        return orderService.cancelOrderPlacement(orderID);
    }
    @PutMapping(value = "/shipOrder")
    public String packageOrder(@RequestParam String address, @RequestParam int orderID)
    {
        return orderService.packageOrder(address,orderID);
    }
    @PutMapping(value = "/cancelOrderShipping")
    public String cancelOrderShipping(@RequestParam String address, @RequestParam int orderID)
    {
        return orderService.cancelOrderShipping(address,orderID);
    }
}
