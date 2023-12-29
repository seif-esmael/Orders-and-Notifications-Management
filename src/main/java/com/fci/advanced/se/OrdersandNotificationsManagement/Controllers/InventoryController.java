package com.fci.advanced.se.OrdersandNotificationsManagement.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fci.advanced.se.OrdersandNotificationsManagement.Services.InventoryService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController
{
    private InventoryService inventoryService = new InventoryService();

    @PostMapping(value = "/addProduct")
    public String addProduct(@RequestBody Product product, @RequestParam int quantity)
    {
        return inventoryService.addProduct(product,quantity);
    }
    @PutMapping(value = "/updateProductQuantity")
    public String updateProductQuantity(@RequestParam int serialNumber,@RequestParam int quantity)
    {
        return inventoryService.updateProductQuantity(serialNumber,quantity);
    }
    @GetMapping(value = "/getAllProducts")
    public String displayAllProducts() throws JsonProcessingException {
        return inventoryService.displayAllProducts();
    }
}
