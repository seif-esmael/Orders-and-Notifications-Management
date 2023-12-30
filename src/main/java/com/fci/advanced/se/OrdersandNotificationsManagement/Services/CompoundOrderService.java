package com.fci.advanced.se.OrdersandNotificationsManagement.Services;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.*;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationTemplate;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.*;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompoundOrderService implements OrderService, Subject
{
    private CompoundOrderObserver observer = new CompoundOrderObserver(this);
    private SimpleOrderService simpleOrderService = new SimpleOrderService();
    private CustomerDatabase customersDatabase = new InMemoryCustomersDatabase();
    private OrderDatabase ordersDatabase = new InMemoryOrdersDatabase();
    private ShippingDatabase shippingsDatabase = new InMemoryShippingsDatabase();

    public Order showOrderDetails(int orderID)
    {
        return ordersDatabase.getOrder(orderID);
    }
    public void notifyObserver(int orderID, NotificationTemplate notificationTemplate)
    {
        this.observer.update(orderID, notificationTemplate);
    }
    public String createCompoundOrder(String username, List<Integer> orderIDs, String address)
    {
        double totalPrice = 0.0;
        CompoundOrder compoundOrder = new CompoundOrder(totalPrice,username,address);
        boolean checkIfThereIsOrderOfYours = false;
        for(Integer i : orderIDs)
        {
            Order order = ordersDatabase.getOrder(i);
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
            if(order.getCustomerName().equals(username))
            {
                checkIfThereIsOrderOfYours = true;
            }
            totalPrice += order.getPrice();
            compoundOrder.addOrder(order);
        }
        if(!checkIfThereIsOrderOfYours)
        {
            return "There is no order of yours in these orders, please add at least one order of your own!";
        }
        compoundOrder.setPrice(totalPrice);
        ordersDatabase.addOrder(compoundOrder);
        return "Order created created successfully with ID: " + compoundOrder.getId();
    }
    public String placeOrder(int orderID)
    {
        Order compoundOrder = ordersDatabase.getOrder(orderID);
        if(compoundOrder == null)
        {
            return "Order is not found";
        }
        if(compoundOrder instanceof SimpleOrder)
        {
            return "The order is not compound order";
        }
        if(compoundOrder.isPlaced())
        {
            return "Order is already placed!";
        }
        List<Order> orders = ((CompoundOrder) compoundOrder).getOrders();
        String problems = "";
        for(Order order : orders)
        {
            Customer customer = customersDatabase.getCustomer(order.getCustomerName());
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
                Customer customer = customersDatabase.getCustomer(order.getCustomerName());
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
        Order compoundOrder = ordersDatabase.getOrder(orderID);
        if(compoundOrder == null)
        {
            return "Order is not found!";
        }
        if(compoundOrder instanceof SimpleOrder)
        {
            return "The order is not compound order";
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
            Customer customer = customersDatabase.getCustomer(order.getCustomerName());
            customer.setBalance(customer.getBalance()+order.getPrice());
            order.setPlaced(false);
        }
        return "Order is unplaced successfully and each order fees is added back to its own customer";
    }
    public String packageOrder(String address, int orderID)
    {
        Shipping shipping = new Shipping(orderID,address);
        Order compoundOrder = ordersDatabase.getOrder(orderID);
        if(compoundOrder == null)
        {
            return "Order is not found";
        }
        if(compoundOrder instanceof SimpleOrder)
        {
            return "The order is not compound order";
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
            Customer customer = customersDatabase.getCustomer(order.getCustomerName());
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
                Customer customer = customersDatabase.getCustomer(order.getCustomerName());
                customer.setBalance(customer.getBalance()-feesForEach);
                order.setBeingShipped(true);
            }
            shippingsDatabase.addShipping(shipping);
            NotificationTemplate compoundShippingTemplate = NotificationTemplate.ShippingCompound;
            notifyObserver(orderID, compoundShippingTemplate);
            return "The Order is placed for shipping with fees " + feesForEach + " for each customer in the order" + ", you can cancel order shipping during the next minute only!";
        }
        return "Couldn't ship the order as " + problems + " balances' is not enough!";
    }
    public String cancelOrderShipping(String address,int orderID)
    {
        Order compoundOrder = ordersDatabase.getOrder(orderID);
        if(compoundOrder == null)
        {
            return "Order is not found";
        }
        if(compoundOrder instanceof SimpleOrder)
        {
            return "The order is not compound order";
        }
        if(!compoundOrder.isBeingShipped())
        {
            return "Order is not placed for shipping!";
        }
        Shipping shipping = shippingsDatabase.getShipping(orderID);
        if(shipping.getCancelPlacementDuration() == 0)
        {
            return "You can't cancel shipping now as its cancel shipping duration is ended!";
        }
        compoundOrder.setBeingShipped(false);
        List<Order> orders = ((CompoundOrder) compoundOrder).getOrders();
        double feesForEach = shipping.getFees()/orders.size();
        shippingsDatabase.removeShipping(shipping);
        for(Order order : orders)
        {
            Customer customer = customersDatabase.getCustomer(order.getCustomerName());
            customer.setBalance(customer.getBalance()+feesForEach);
            order.setBeingShipped(false);
        }
        return "Shipping Canceled and its fees is added back to each customer balance";
    }
    public Order getOrder(int orderID)
    {
        return ordersDatabase.getOrder(orderID);
    }
    public String getOrderCustomers(int orderID)
    {
        Order order = getOrder(orderID);
        if(order instanceof SimpleOrder)
        {
            return "The order is not compound order";
        }
        String customerNames = "";
        for(Order o : ((CompoundOrder) order).getOrders())
        {
            customerNames += o.getCustomerName();
            customerNames += ", ";
        }
        customerNames = customerNames.substring(0,customerNames.length()-2);
        return customerNames;
    }
}
