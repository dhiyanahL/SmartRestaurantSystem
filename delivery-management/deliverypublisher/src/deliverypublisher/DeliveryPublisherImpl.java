package deliverypublisher;

import java.util.ArrayList;
import java.util.List;

import ordermanagementproducer.DeliveryOrder;

public class DeliveryPublisherImpl implements DeliveryPublisher {
	
	private List<DeliveryOrder> deliveryOrders = new ArrayList<>();
	
	// Constants for delivery calculations
    private static final double BASE_DELIVERY_FEE = 5.0;  // Fixed fee per order
    private static final double DISTANCE_FEE_PER_MILE = 2.0;  // Fee per mile
    private static final double ITEM_FEE_PER_ITEM = 0.5;  // Fee per item

    @Override
    public int calculateTotalQuantity(List<DeliveryOrder> orders) {
        int totalItems = 0;
        for (DeliveryOrder order : orders) {
            totalItems += order.getQuantity();  // Add quantity for each order
        }
        return totalItems;
    }

    @Override
    public int calculateDeliveryTime(List<DeliveryOrder> orders) {
        int totalTime = 0;
        int timePerItem = 10;  // 10 minutes per item for delivery

        for (DeliveryOrder order : orders) {
            totalTime += order.getQuantity() * timePerItem;  // Multiply by quantity for each order
        }
        return totalTime;
    }

    @Override
    public double calculateDeliveryPrice(List<DeliveryOrder> orders, double distance) {
        double totalPrice = 0.0;

        for (DeliveryOrder order : orders) {
            double distanceFee = distance * DISTANCE_FEE_PER_MILE;  // Fee based on distance
            double itemFee = order.getQuantity() * ITEM_FEE_PER_ITEM;  // Fee based on quantity of items

            // Calculate total delivery price
            totalPrice += BASE_DELIVERY_FEE + distanceFee + itemFee;
        }
        return totalPrice;
    }

    @Override
    public void generateReport(List<DeliveryOrder> orders, double distance) {
        int totalItems = calculateTotalQuantity(orders);
        int totalTime = calculateDeliveryTime(orders);
        double totalPrice = calculateDeliveryPrice(orders, distance);

        // Print the report
        System.out.println("===== Delivery Report =====");
        System.out.println("Total Items Ordered: " + totalItems);
        System.out.println("Total Estimated Time: " + totalTime + " minutes");
        System.out.println("Total Estimated Delivery Price: $" + totalPrice);
        System.out.println("---------------------------");
    }
    
    public void receiveDeliveryOrders(List<DeliveryOrder> orders) {
        // Store the received orders in the internal list
        deliveryOrders.addAll(orders);
        System.out.println("📦 Delivery orders received and stored.");
    }
    
}
