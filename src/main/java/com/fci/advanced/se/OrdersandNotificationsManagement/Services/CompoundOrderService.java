package com.fci.advanced.se.OrdersandNotificationsManagement.Services;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.CustomersDummyDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.OrdersDummyDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.ShippingsDummyDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationTemplate;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.*;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompoundOrderService
{
    private CompoundOrderObserver observer = new CompoundOrderObserver(); // this
    private SimpleOrderService simpleOrderService = new SimpleOrderService();

    public Order showOrderDetails(int orderID)
    {
        return OrdersDummyDatabase.getOrder(orderID);
    }
    public void notifyObserver(int orderID, NotificationTemplate notificationTemplate)
    {
        this.observer.update(orderID, notificationTemplate);
    }
    public String createCompoundOrder(String username, List<Integer> orderIDs, String address)
    {
        double totalPrice = 0.0;
        CompoundOrder compoundOrder = new CompoundOrder(totalPrice,username,address);
        for(Integer i : orderIDs)
        {
            Order order = OrdersDummyDatabase.getOrder(i);
            if(order == null)
            {
                return "The order with ID: " + i + " is not found!";
            }
            if(order.isPlaced())
            {
                return "The order with ID: " + i + " is already placed!";
            }
            if(order.isBeingShipped())
            {
                return "The order with ID: " + i + " is already being shipped!";
            }
            totalPrice += order.getPrice();
            compoundOrder.addOrder(order);
        }
        compoundOrder.setPrice(totalPrice);
        OrdersDummyDatabase.addOrder(compoundOrder);
        return "Order created created successfully with ID: " + compoundOrder.getId();
    }
    public String placeOrder(int orderID)
    {
        Order compoundOrder = OrdersDummyDatabase.getOrder(orderID);
        if(compoundOrder == null)
        {
            return "Order is not found";
        }
        if(compoundOrder.isPlaced())
        {
            return "Order is already placed!";
        }
        List<Order> orders = ((CompoundOrder) compoundOrder).getOrders();
        String problems = "";
        for(Order order : orders)
        {
            Customer customer = CustomersDummyDatabase.getCustomer(order.getCustomerName());
            if(customer.getBalance() < order.getPrice())
            {
                problems += customer.getUserName() + ' ';
            }
        }
        if(problems.equals(""))
        {
            compoundOrder.setPlaced(true);
            for(Order order : orders)
            {
                Customer customer = CustomersDummyDatabase.getCustomer(order.getCustomerName());
                customer.setBalance(customer.getBalance()-order.getPrice());
                order.setPlaced(true);
            }
            NotificationTemplate compoundPlacementTemplate = NotificationTemplate.PlacingCompound;
            notifyObserver(orderID, compoundPlacementTemplate);
            return "Order is placed successfully and each order fees is deducted from its own customer";
        }
        return "Couldn't place the order as " + problems + " balances' is not enough!";
    }
    public String cancelOrderPlacement(int orderID)
    {
        Order compoundOrder = OrdersDummyDatabase.getOrder(orderID);
        if(compoundOrder == null)
        {
            return "Order is not found!";
        }
        if(!compoundOrder.isPlaced())
        {
            return "Order is not yet placed!";
        }
        if(compoundOrder.isBeingShipped())
        {
            return "Order is already being shipped, cancel shipping first!";
        }
        compoundOrder.setPlaced(false);
        List<Order> orders = ((CompoundOrder) compoundOrder).getOrders();
        for(Order order : orders)
        {
            Customer customer = CustomersDummyDatabase.getCustomer(order.getCustomerName());
            customer.setBalance(customer.getBalance()+order.getPrice());
            order.setPlaced(false);
        }
        return "Order is unplaced successfully and each order fees is added back to its own customer";
    }
    public String packageOrder(String address, int orderID)
    {
        Shipping shipping = new Shipping(orderID,address);
        Order compoundOrder = OrdersDummyDatabase.getOrder(orderID);
        if(compoundOrder == null)
        {
            return "Order is not found";
        }
        if(!compoundOrder.isPlaced())
        {
            return "Order is not placed, place the order first to ship it!";
        }
        if(compoundOrder.isBeingShipped())
        {
            return "Order is already being shipped!";
        }
        List<Order> orders = ((CompoundOrder) compoundOrder).getOrders();
        double feesForEach = shipping.getFees()/orders.size();
        String problems = "";
        for(Order order : orders)
        {
            Customer customer = CustomersDummyDatabase.getCustomer(order.getCustomerName());
            if(customer.getBalance() < feesForEach)
            {
                problems += customer.getUserName() + ' ';
            }
        }
        if(problems.equals(""))
        {
            compoundOrder.setBeingShipped(true);
            for(Order order : orders)
            {
                Customer customer = CustomersDummyDatabase.getCustomer(order.getCustomerName());
                customer.setBalance(customer.getBalance()-feesForEach);
                order.setBeingShipped(true);
            }
            ShippingsDummyDatabase.addShipping(shipping);
            NotificationTemplate compoundShippingTemplate = NotificationTemplate.ShippingCompound;
            notifyObserver(orderID, compoundShippingTemplate);
            return "The Order is placed for shipping with fees " + feesForEach + " for each customer in the order";
        }
        return "Couldn't ship the order as " + problems + " balances' is not enough!";
    }
    public String cancelOrderShipping(String address,int orderID)
    {
        Order compoundOrder = OrdersDummyDatabase.getOrder(orderID);
        if(compoundOrder == null)
        {
            return "Order is not found";
        }
        if(!compoundOrder.isBeingShipped())
        {
            return "Order is not placed for shipping!";
        }
        Shipping shipping = ShippingsDummyDatabase.getShipping(orderID);
        if(shipping.getCancelPlacementDuration() == 0)
        {
            return "You can't cancel shipping now as its cancel shipping duration is ended!";
        }
        compoundOrder.setBeingShipped(false);
        List<Order> orders = ((CompoundOrder) compoundOrder).getOrders();
        double feesForEach = shipping.getFees()/orders.size();
        ShippingsDummyDatabase.removeShipping(shipping);
        for(Order order : orders)
        {
            Customer customer = CustomersDummyDatabase.getCustomer(order.getCustomerName());
            customer.setBalance(customer.getBalance()+feesForEach);
            order.setBeingShipped(false);
        }
        return "Shipping Canceled and its fees is added back to each customer balance";
    }
}
