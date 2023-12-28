//package com.fci.advanced.se.OrdersandNotificationsManagement.Controllers;
//
//import com.fci.advanced.se.OrdersandNotificationsManagement.Services.CartService;
//import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/cart")
//public class CartController {
//    private CartService cartService;
//
//    @PutMapping(value = "/addproductQuantity")
//    public String addProductQuantity(@RequestParam String customerUsername,@RequestParam int serialNo,@RequestParam int quantity)
//    {
//        return cartService.addProductQuantity(customerUsername,serialNo,quantity);
//    }
//
//    @PutMapping(value = "/removeproductQuantity")
//    public String removeProductQuantity(@RequestParam String customerUsername,@RequestParam int serialNo,@RequestParam int quantity)
//    {
//        return cartService.removeProductQuantity(customerUsername,serialNo,quantity);
//    }
//
//    @PostMapping(value = "/checkout")
//    public Order checkout(@RequestParam String customerUsername,@RequestParam String address)
//    {
//        return cartService.checkout(customerUsername,address);
//    }
//}
