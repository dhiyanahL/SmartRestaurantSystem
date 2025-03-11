package deliverysubscriber;

import deliveryapi.DeliverySubscriber; // Import from deliveryapi
import ordermanagementosgi.DeliveryOrder;

public class DeliverySubscriberImpl implements DeliverySubscriber {
    @Override
    public void onDeliveryCompleted(DeliveryOrder deliveryOrder) {
    	 System.out.println("Delivery completed for order: " + deliveryOrder.getDeliveryOrderId());
         System.out.println("Customer Name: " + deliveryOrder.getCustomerName());
         System.out.println("Order Details: " + deliveryOrder.toString());
    }
}
