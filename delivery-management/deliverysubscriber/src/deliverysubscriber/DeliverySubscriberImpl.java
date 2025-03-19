package deliverysubscriber;

import deliveryapi.DeliverySubscriber; // Import from deliveryapi
import ordermanagementproducer.DeliveryOrder;
import deliveryapi.DeliveryPublisher; // Import DeliveryPublisher

public class DeliverySubscriberImpl implements DeliverySubscriber {

    private DeliveryPublisher deliveryPublisher; // Reference to DeliveryPublisher

    // Constructor to inject the DeliveryPublisher (this could also be done via a setter)
    public DeliverySubscriberImpl(DeliveryPublisher deliveryPublisher) {
        this.deliveryPublisher = deliveryPublisher;
    }

    @Override
    public void onDeliveryCompleted(DeliveryOrder deliveryOrder) {
        // Handle delivery completed event
        System.out.println("Delivery completed for order: " + deliveryOrder.getDeliveryOrderId());
        System.out.println("Customer Name: " + deliveryOrder.getCustomerName());
        System.out.println("Order Details: " + deliveryOrder.toString());

        // Use DeliveryPublisher's methods (for example, generate a report after the delivery)
        double distance = 10.0; // Example: assuming 10 miles as distance
        deliveryPublisher.generateReport(deliveryPublisher.getDeliveryOrders(), distance);
    }
}
