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
    private OrderObserver observer = new OrderObserver(this);

    public SimpleOrderService()
    {
        if(OrdersDummyDatabase.getSize() == 0)
        {
            OrdersDummyDatabase.addOrder(new SimpleOrder(50,"Kiro123","hadayek-elahram"));
            OrdersDummyDatabase.addOrder(new SimpleOrder(100,"Kiro123","dokki"));
            OrdersDummyDatabase.addOrder(new SimpleOrder(520,"Seif123","ismailia"));
            OrdersDummyDatabase.addOrder(new SimpleOrder(90,"Yousef123","giza"));
        }
    }
    @Override
    public Order showOrderDetails(int orderID)
    {
        return OrdersDummyDatabase.getOrder(orderID);
    }

    public void notifyObserver(int orderID, NotificationTemplate template)
    {
        this.observer.update(orderID,template);
    }
    @Override
    public String placeOrder(int orderID)
    {
        Order order = OrdersDummyDatabase.getOrder(orderID);
        if(order == null)
        {
            return "Order is not found";
        }
        if(order.isPlaced())
        {
            return "Order is already placed!";
        }
        Customer customer = CustomersDummyDatabase.getCustomer(order.getCustomerName());
        if(customer.getBalance() < order.getPrice())
        {
            return "Your balance is not enough!";
        }
        order.setPlaced(true);
        customer.setBalance(customer.getBalance()-order.getPrice());
        NotificationTemplate simplePlacementTemplate = NotificationTemplate.PlacingSimple;
        notifyObserver(orderID, simplePlacementTemplate);
        return "Order placed and its fees is removed from your balance";
    }
    public String cancelOrderPlacement(int orderID)
    {
        Order order = OrdersDummyDatabase.getOrder(orderID);
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
        Customer customer = CustomersDummyDatabase.getCustomer(order.getCustomerName());
        customer.setBalance(customer.getBalance()+order.getPrice());
        return "Order unplaced and its fees is added back to your balance";
    }
    public String packageOrder(String address, int orderID)
    {
        Shipping shipping = new Shipping(orderID,address);
        Order order = OrdersDummyDatabase.getOrder(orderID);
        if(order == null)
        {
            return "Order is not found";
        }
        Customer customer = CustomersDummyDatabase.getCustomer(order.getCustomerName());
        if(shipping.getFees() > customer.getBalance())
        {
            return "Your balance is not enough for shipping!";
        }
        order.setBeingShipped(true);
        customer.setBalance(customer.getBalance() - shipping.getFees());
        ShippingsDummyDatabase.addShipping(shipping);
        return "The Order is placed for shipping with fees " + shipping.getFees();
    }
    public String cancelOrderShipping(String address,int orderID)
    {
        Order order = OrdersDummyDatabase.getOrder(orderID);
        if(order == null)
        {
            return "Order is not found";
        }
        if(!order.isBeingShipped())
        {
            return "Order is not placed for shipping!";
        }
        Shipping shipping = ShippingsDummyDatabase.getShipping(orderID);
        if(shipping.getCancelPlacementDuration() == 0)
        {
            return "You can't cancel shipping now as its cancel shipping duration is ended!";
        }
        ShippingsDummyDatabase.removeShipping(shipping);
        Customer customer = CustomersDummyDatabase.getCustomer(order.getCustomerName());
        customer.setBalance(customer.getBalance() + shipping.getFees());
        order.setBeingShipped(false);
        return "Shipping Canceled and its fees is added back to your balance";
    }
    public Order getOrder(int orderID)
    {
        return OrdersDummyDatabase.getOrder(orderID);
    }
}
