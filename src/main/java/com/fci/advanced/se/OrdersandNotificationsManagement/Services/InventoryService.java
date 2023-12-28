package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Inventory;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Product;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Category.*;

@Service
public class InventoryService {
    private Inventory inv;

    public InventoryService() {
        this.inv = new Inventory();
        if (this.inv.inventory.size() == 0)
        {
            inv.inventory.put(new Product(1,"iphone","seif",SmartPhones,5000.0),3);
            inv.inventory.put(new Product(2,"samsungA20","kiro",SmartPhones,2000.0),10);
            inv.inventory.put(new Product(3,"LG_TV","yousef",SmartTVs,1500.0),4);
            inv.inventory.put(new Product(4,"canonCamera","panda",Cameras,7000.0),1);
            inv.inventory.put(new Product(5,"Huawei","nagy",HeadPhones,200.0),1);
            inv.inventory.put(new Product(6,"airpods","zoz",HeadPhones,2000.0),1);
        }
    }

    public String addProduct(Product product,int quantity)
    {
        for (Map.Entry<Product, Integer> entry : inv.inventory.entrySet()) {
            if (entry.getKey().getSerialNumber() == product.getSerialNumber())
            {
                return "This Serial Number already exists!";
            }
        }
        inv.inventory.put(product,quantity);
        return "Product added Successfully";
    }

    public String updateProductQuantity(int serialNumber, int quantity)
    {
        for (Map.Entry<Product, Integer> entry : inv.inventory.entrySet()) {
            if (entry.getKey().getSerialNumber() == serialNumber)
            {
                int quant = entry.getValue();
                Product p = entry.getKey();
                inv.inventory.remove(entry.getKey());
                if (quant - quantity > 0)
                    inv.inventory.put(p,quant - quantity);
                return "Quantity Updated Successfully";
            }
        }
        return "Product doesn't exist!";
    }

    public boolean isAvailableQuantity(int serialNumber, int quantity)
    {
        for (Map.Entry<Product, Integer> entry : inv.inventory.entrySet()) {
            if (entry.getKey().getSerialNumber() == serialNumber)
            {
                if(entry.getValue() < quantity)
                    return false;
                return true;
            }
        }
        return false;
    }

    public String displayAllProducts() throws JsonProcessingException {
        String result = "";

        for (Map.Entry<Product, Integer> entry : inv.inventory.entrySet()) {
            ObjectMapper objectMapper = new ObjectMapper();

            result += objectMapper.writeValueAsString(entry.getKey()) + "\n";
            result += "quantity: " + entry.getValue().toString() + "\n";
        }
        return result;
    }

    public Product getProductBySerialNumber(int serialNo)
    {
        for (Map.Entry<Product, Integer> entry : inv.inventory.entrySet()) {
            if (entry.getKey().getSerialNumber() == serialNo)
            {
                return entry.getKey();
            }
        }
        return null;
    }
}
