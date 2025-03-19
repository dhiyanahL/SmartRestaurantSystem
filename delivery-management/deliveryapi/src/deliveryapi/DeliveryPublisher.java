package deliveryapi;

import ordermanagementproducer.DeliveryOrder;
import java.util.List;

public interface DeliveryPublisher {
    // Method to calculate total quantity of items
    int calculateTotalQuantity(List<DeliveryOrder> orders);
    
    // Method to calculate total delivery time
    int calculateDeliveryTime(List<DeliveryOrder> orders);
    
    // Method to calculate total delivery price
    double calculateDeliveryPrice(List<DeliveryOrder> orders, double distance);
    
    // Method to generate a delivery report
    void generateReport(List<DeliveryOrder> orders, double distance);

    // Add the getDeliveryOrders method to the interface
    List<DeliveryOrder> getDeliveryOrders();  // This is the missing method
    
    // Add the receiveDeliveryOrders method to the interface
    void receiveDeliveryOrders(List<DeliveryOrder> orders);  // The method to receive orders
}
