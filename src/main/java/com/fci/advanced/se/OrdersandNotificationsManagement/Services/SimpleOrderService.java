package com.fci.advanced.se.OrdersandNotificationsManagement.Services;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.CustomersDummyDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.OrdersDummyDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.DummyDatabases.ShippingsDummyDatabase;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.NotificationTemplate;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Order;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.OrderObserver;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.Shipping;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering.SimpleOrder;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.User.Customer;
import org.springframework.stereotype.Service;

@Service
public class SimpleOrderService implements OrderService
{
    public OrdersDummyDatabase orders = new OrdersDummyDatabase();
    CustomersDummyDatabase customers = new CustomersDummyDatabase();
    ShippingsDummyDatabase shippings = new ShippingsDummyDatabase();
    private OrderObserver observer = new OrderObserver(this);

    public SimpleOrderService()
    {
        orders.addOrder(new SimpleOrder(50,"yousef","hadayek-elahram"));
        orders.addOrder(new SimpleOrder(100,"mo","dokki"));
        orders.addOrder(new SimpleOrder(520,"ahmed","ismailia"));
        orders.addOrder(new SimpleOrder(90,"ayman","giza"));
    }
    @Override
    public Order showOrderDetails(int orderID)
    {
        return orders.getOrder(orderID);
    }

    public void notifyObserver(int orderID, NotificationTemplate template)
    {
        this.observer.update(orderID,template);
    }
    @Override
    public String placeOrder(int orderID)
    {
        Order order = orders.getOrder(orderID);
        if(order == null)
        {
            return "Order is not found";
        }
        if(order.isPlaced())
        {
            return "Order is already placed!";
        }
        order.setPlaced(true);
        Customer customer = customers.getCustomer(order.getCustomerName());
        if(customer.getBalance() < order.getPrice())
        {
            return "Your balance is not enough!";
        }
        customer.setBalance(customer.getBalance()-order.getPrice());
        NotificationTemplate simplePlacementTemplate = NotificationTemplate.PlacingSimple;
        notifyObserver(orderID, simplePlacementTemplate);
        return "Order placed and its fees is removed from your balance";
    }
    public String cancelOrderPlacement(int orderID)
    {
        Order order = orders.getOrder(orderID);
        if(order == null)
        {
            return "Order is not found";
        }
        if(!order.isPlaced())
        {
            return "Order is not placed";
        }
        if(order.isBeingShipped())
        {
            return "Order is already being shipped, cancel shipment first!";
        }
        order.setPlaced(false);
        Customer customer = customers.getCustomer(order.getCustomerName());
        customer.setBalance(customer.getBalance()+order.getPrice());
        return "Order unplaced and its fees is added back to your balance";
    }
    public String packageOrder(String address, int orderID)
    {
        Shipping shipping = new Shipping(orderID,address);
        Order order = orders.getOrder(orderID);
        if(order == null)
        {
            return "Order is not found";
        }
        Customer customer = customers.getCustomer(order.getCustomerName());
        if(shipping.getFees() > customer.getBalance())
        {
            return "Your balance is not enough for shipping!";
        }
        order.setBeingShipped(true);
        customer.setBalance(customer.getBalance() - shipping.getFees());
        shippings.addShipping(shipping);
        return "The Order is placed for shipping with fees " + shipping.getFees();
    }
    public String cancelOrderShipping(String address,int orderID)
    {
        Order order = orders.getOrder(orderID);
        if(order == null)
        {
            return "Order is not found";
        }
        if(!order.isBeingShipped())
        {
            return "Order is not placed for shipping!";
        }
        Shipping shipping = shippings.getShipping(orderID);
        if(shipping.getCancelPlacementDuration() == 0)
        {
            return "You can't cancel shipping now as its cancel shipping duration is ended!";
        }
        shippings.removeShipping(shipping);
        Customer customer = customers.getCustomer(order.getCustomerName());
        customer.setBalance(customer.getBalance() + shipping.getFees());
        order.setBeingShipped(false);
        return "Shipping Canceled and its fees is added back to your balance";
    }
    public Order getOrder(int orderID)
    {
        return orders.getOrder(orderID);
    }
}
