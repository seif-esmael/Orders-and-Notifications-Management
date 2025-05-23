package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.CustomerDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.InMemoryCustomersDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.InMemoryOrdersDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.OrderDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.SimpleOrder;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Product;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Shopping.Cart;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {
    private Cart cart;
    private CustomerService customerService = new CustomerService();
    private InventoryService inventoryService = new InventoryService();
    private SimpleOrderService orderService = new SimpleOrderService();
    private CustomerDatabase customersDatabase = new InMemoryCustomersDatabase();
    private OrderDatabase ordersDatabase = new InMemoryOrdersDatabase();

    public CartService() {
        this.cart = new Cart();
    }

    public String addProductQuantity(String customerUsername,int serialNo, int quantity)
    {
        this.cart = customersDatabase.getUserCart(customerUsername);
        Product product = inventoryService.getProductBySerialNumber(serialNo);
        int newQuantity = quantity;
        if(cart.products.containsKey(product))
        {
            quantity+= cart.products.get(product);
        }
        if(inventoryService.isAvailableQuantity(serialNo, quantity))
        {
            cart.products.remove(product);
            cart.products.put(product,quantity);
            cart.setTotalPrice(cart.getTotalPrice() + (product.getPrice() * newQuantity));
            return "Product Quantity Added to Cart";
        }
        return "Unavailable Product!";
    }

    public String removeProductQuantity(String customerUsername, int serialNo, int quantity)
    {
        this.cart = customersDatabase.getUserCart(customerUsername);
        if(isAvailableQuantity(serialNo,quantity))
        {
            Product product = inventoryService.getProductBySerialNumber(serialNo);
            int quant = cart.products.get(product);
            cart.products.remove(product);
            cart.setTotalPrice(cart.getTotalPrice() - (product.getPrice() * quantity));
            if (quant - quantity > 0)
                cart.products.put(product,quant - quantity);
            return "Product Quantity Removed Successfully";
        }
        return "Quantity in the cart is less than the quantity you want to remove!";
    }

    public boolean isAvailableQuantity(int serialNo, int quantity)
    {
        for(Map.Entry<Product, Integer> entry : cart.products.entrySet())
        {
            if(entry.getKey().getSerialNumber() == serialNo)
            {
                if(entry.getValue() >= quantity)
                {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    public Order checkout(String customerUsername, String address)
    {
        this.cart = customersDatabase.getUserCart(customerUsername);
        SimpleOrder simpleorder = new SimpleOrder(cart.getTotalPrice(),customerUsername,address);
        simpleorder.addProducts(this.cart);
        ordersDatabase.addOrder(simpleorder);
        for(Map.Entry<Product, Integer> entry : cart.products.entrySet())
        {
            inventoryService.updateProductQuantity(entry.getKey().getSerialNumber(),entry.getValue());
        }
        this.cart.clear();
        return simpleorder;
    }
}
