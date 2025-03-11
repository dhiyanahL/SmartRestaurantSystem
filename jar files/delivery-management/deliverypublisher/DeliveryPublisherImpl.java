package deliverypublisher;

import ordermanagementosgi.DeliveryOrder;
import java.util.ArrayList;
import java.util.List;

public class DeliveryPublisherImpl implements DeliveryPublisher {
    private List<DeliveryOrder> deliveryOrders;

    public DeliveryPublisherImpl() {
        this.deliveryOrders = new ArrayList<>();
    }

    @Override
    public void deliverOrder() {
        if (deliveryOrders != null && !deliveryOrders.isEmpty()) {
            for (DeliveryOrder order : deliveryOrders) {
                System.out.println("Delivering order: " + order.getDeliveryOrderId());
            }
        } else {
            System.out.println("No delivery orders to process.");
        }
    }

    public void setDeliveryOrders(List<DeliveryOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }
}
