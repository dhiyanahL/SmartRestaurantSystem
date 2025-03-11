package deliverypublisher;

import ordermanagementosgi.DeliveryOrder;
import ordermanagementosgi.OrderService;
import deliverysubscriber.DeliverySubscriber;
import java.util.ArrayList;
import java.util.List;

public class DeliveryPublisherImpl implements DeliveryPublisher {

    private OrderService orderService; // Reference to OrderService to fetch delivery orders
    private List<DeliverySubscriber> subscribers; // List of subscribers to notify about delivery completion

    // Constructor accepts an OrderService and initializes the list of subscribers
    public DeliveryPublisherImpl(OrderService orderService) {
        this.orderService = orderService;
        this.subscribers = new ArrayList<>(); // Initialize the subscribers list
    }

    @Override
    public void deliverOrder() {
        try {
            List<DeliveryOrder> deliveryOrders = orderService.getDeliveryOrders();
            
            if (deliveryOrders != null && !deliveryOrders.isEmpty()) {
                DeliveryOrder deliveryOrder = deliveryOrders.get(0);
                System.out.println("📦 Delivery in Progress for Order ID: " + deliveryOrder.getDeliveryOrderId());
                notifySubscribers(deliveryOrder);
            } else {
                System.out.println("No Delivery Orders to process.");
            }
        } catch (Exception e) {
            System.err.println("Error processing delivery order: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addSubscriber(DeliverySubscriber subscriber) {
        try {
            if (subscriber != null) {
                subscribers.add(subscriber);
                System.out.println("Subscriber added.");
            } else {
                System.out.println("Attempted to add a null subscriber.");
            }
        } catch (Exception e) {
            System.err.println("Error adding subscriber: " + e.getMessage());
        }
    }

    public void removeSubscriber(DeliverySubscriber subscriber) {
        try {
            if (subscriber != null && subscribers.contains(subscriber)) {
                subscribers.remove(subscriber);
                System.out.println("Subscriber removed.");
            } else {
                System.out.println("Subscriber not found.");
            }
        } catch (Exception e) {
            System.err.println("Error removing subscriber: " + e.getMessage());
        }
    }

    private void notifySubscribers(DeliveryOrder deliveryOrder) {
        System.out.println("Notifying Subscribers about Delivery Completion...");
        for (DeliverySubscriber subscriber : subscribers) {
            try {
                subscriber.onDeliveryCompleted(deliveryOrder);
            } catch (Exception e) {
                System.err.println("Error notifying subscriber: " + e.getMessage());
            }
        }
    }
}