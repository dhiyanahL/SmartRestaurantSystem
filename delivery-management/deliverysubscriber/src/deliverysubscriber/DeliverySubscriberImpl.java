package deliverysubscriber;

import ordermanagementosgi.DeliveryOrder;

public class DeliverySubscriberImpl implements DeliverySubscriber {

    @Override
    public void onDeliveryCompleted(DeliveryOrder deliveryOrder) {
        try {
            System.out.println("📦 Delivery Completed!");
            System.out.println("Order ID: " + deliveryOrder.getDeliveryOrderId());
            System.out.println("Item: " + deliveryOrder.getType());
            System.out.println("Quantity: " + deliveryOrder.getQuantity());
            System.out.println("Customer: " + deliveryOrder.getCustomerName());
            System.out.println("Phone: " + deliveryOrder.getPhone());
            System.out.println("Address: " + deliveryOrder.getAddress());
        } catch (Exception e) {
            System.err.println("Error processing delivery completion: " + e.getMessage());
        }
    }
}
