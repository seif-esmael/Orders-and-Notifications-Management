package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.CategoryType;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Inventory;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.CategoryType.*;

@Service
public class InventoryService
{
    private Inventory inv;

    public InventoryService()
    {
        this.inv = new Inventory();
        if(this.inv.inventory.size() == 0)
        {
            addCategory(Electronics);
            addCategory(MusicalInstruments);
            addCategory(Sports);
            inv.inventory.get(Electronics).put(new Product(1,"iPhone 13 pro max","Ahmed",Electronics,5000.0),3);
            inv.inventory.get(Electronics).put(new Product(2,"samsungA20","Ali",Electronics,2000.0),10);
            inv.inventory.get(Electronics).put(new Product(3,"LG_TV","Youssef", Electronics,1500.0),4);
            inv.inventory.get(MusicalInstruments).put(new Product(4,"Guitar","Ahmed", MusicalInstruments,7000.0),5);
            inv.inventory.get(MusicalInstruments).put(new Product(5,"Violin","Mohamed",MusicalInstruments,200.0),1);
            inv.inventory.get(Sports).put(new Product(6,"AirHockey","Ziad",Sports,2000.0),1);
            inv.inventory.get(Sports).put(new Product(7,"Ball","Ali",Sports,1000.0),4);
        }
    }
    public void addCategory(CategoryType categoryType)
    {
        Map<Product, Integer> map = new HashMap<>();
        inv.inventory.put(categoryType,map);
    }
    public String addProduct(Product product, int quantity)
    {
        for(Map.Entry<Product, Integer> entry : inv.inventory.get(product.getCategory()).entrySet())
        {
            if (entry.getKey().getSerialNumber() == product.getSerialNumber())
            {
                return "This Serial Number already exists!";
            }
        }
        inv.inventory.get(product.getCategory()).put(product,quantity);
        return "Product added Successfully";
    }
    public String updateProductQuantity(int serialNumber, int quantity)
    {
        Product product = getProductBySerialNumber(serialNumber);
        for(Map.Entry<Product, Integer> entry : inv.inventory.get(product.getCategory()).entrySet())
        {
            if(entry.getKey().getSerialNumber() == serialNumber)
            {
                int quant = entry.getValue();
                Product p = entry.getKey();
                inv.inventory.get(product.getCategory()).remove(entry.getKey());
                if (quant - quantity > 0)
                    inv.inventory.get(product.getCategory()).put(p,quant - quantity);
                return "Quantity Updated Successfully";
            }
        }
        return "Product doesn't exist!";
    }
    public boolean isAvailableQuantity(int serialNumber, int quantity)
    {
        Product product = getProductBySerialNumber(serialNumber);
        if(product == null)
        {
            return false;
        }
        for(Map.Entry<Product, Integer> entry : inv.inventory.get(product.getCategory()).entrySet())
        {
            if(entry.getKey().getSerialNumber() == serialNumber)
            {
                if(entry.getValue() < quantity)
                    return false;
                return true;
            }
        }
        return false;
    }

    public String displayAllProducts() throws JsonProcessingException
    {
        String result = "";
        for (Map.Entry<CategoryType, Map<Product,Integer>> entry : inv.inventory.entrySet())
        {
            result += "Category: ";
            result += entry.getKey().toString();
            result += "\nNumber of products: " + entry.getValue().size();
            result += "\n";
            result += "Products: \n";
            for(Map.Entry<Product, Integer> entry1 : entry.getValue().entrySet())
            {
                ObjectMapper objectMapper = new ObjectMapper();
                result += objectMapper.writeValueAsString(entry1.getKey()) + "\n";
                result += "Quantity: " + entry1.getValue().toString() + "\n";
            }
            result += '\n';
        }
        return result;
    }

    public Product getProductBySerialNumber(int serialNo)
    {
        for (Map.Entry<CategoryType, Map<Product,Integer>> entry : inv.inventory.entrySet())
        {
            for(Map.Entry<Product, Integer> entry1 : entry.getValue().entrySet())
            {
                if(entry1.getKey().getSerialNumber() == serialNo)
                {
                    return entry1.getKey();
                }
            }
        }
        return null;
    }
}
