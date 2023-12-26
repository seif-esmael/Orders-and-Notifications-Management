package com.fci.advanced.se.OrdersandNotificationsManagement.Services;

import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.SimpleOrder;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Products.Product;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Shopping.Cart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartService {
    private Cart cart;
    private CustomerService customerService = new CustomerService();
    private InventoryService inventoryService = new InventoryService();
    private OrderService orderService = new OrderService();

    public CartService() {
        this.cart = new Cart();
    }

    public String addProductQuantity(String customerUsername,int serialNo, int quantity)
    {
        this.cart = customerService.getUserCart(customerUsername);
        if (inventoryService.isAvailableQuantity(serialNo, quantity))
        {
            Product product = inventoryService.getProductBySerialNumber(serialNo);
            inventoryService.updateProductQuantity(serialNo,quantity);
            if(cart.products.containsKey(product))
            {
                quantity+= cart.products.get(product);
                cart.products.remove(product);
            }
            cart.products.put(inventoryService.getProductBySerialNumber(serialNo),quantity);
            cart.setTotalPrice(cart.getTotalPrice() + (product.getPrice() * quantity));
            return "Product Quantity Added to Cart";
        }
        return "Unavailable Product!";
    }

    public String removeProductQuantity(String customerUsername,int serialNo, int quantity)
    {
        this.cart = customerService.getUserCart(customerUsername);
        if (isAvailableQuantity(serialNo,quantity))
        {
            Product product = inventoryService.getProductBySerialNumber(serialNo);
            inventoryService.updateProductQuantity(serialNo,-quantity);
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
        for (Map.Entry<Product, Integer> entry : cart.products.entrySet()) {
            if (entry.getKey().getSerialNumber() == serialNo)
            {
                if (entry.getValue() > quantity)
                    return true;
                return false;
            }
        }
        return false;
    }

    public Order checkout(String customerUsername,String address)
    {
        this.cart = customerService.getUserCart(customerUsername);
        SimpleOrder Simpleorder = new SimpleOrder(cart.getTotalPrice(),customerUsername,address);
        Simpleorder.addProducts(this.cart);
        orderService.addOrder(Simpleorder);
        return Simpleorder;
    }
}
