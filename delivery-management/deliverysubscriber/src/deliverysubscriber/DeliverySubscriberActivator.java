package deliverysubscriber;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import deliverypublisher.DeliveryPublisherImpl;
import ordermanagementproducer.DeliveryOrder;
import ordermanagementproducer.OrderService;
import deliverypublisher.DeliveryPublisher;

public class DeliverySubscriberActivator implements BundleActivator {
	
	private ServiceReference<?> orderServiceReference;
    private ServiceReference<?> deliveryProducerReference;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("🔍 Searching for OrderService and DeliveryProducer...");

        // Get OrderService
        orderServiceReference = bundleContext.getServiceReference(OrderService.class.getName());
        if (orderServiceReference == null) {
            System.out.println("❌ OrderService Not Found.");
            return;
        }
        OrderService orderService = (OrderService) bundleContext.getService(orderServiceReference);

        // Get DeliveryProducer
        deliveryProducerReference = bundleContext.getServiceReference(DeliveryPublisher.class.getName());
        if (deliveryProducerReference == null) {
            System.out.println("❌ DeliveryProducer Not Found.");
            return;
        }
        DeliveryPublisher deliveryProducer = (DeliveryPublisher) bundleContext.getService(deliveryProducerReference);

        // Start Delivery Process
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Delivery Person Name: ");
        String deliveryPerson = scanner.nextLine();
        deliveryProducer.assignDeliveryPerson(deliveryPerson);

        // Get Delivery Orders from OrderService
        List<DeliveryOrder> deliveryOrders = orderService.getDeliveryOrders();
        for (DeliveryOrder order : deliveryOrders) {
            deliveryProducer.addDeliveryOrder(order);
        }

        // Generate Report and Start Delivery
        deliveryProducer.generateDeliveryReport();
        deliveryProducer.startDelivery();
        deliveryProducer.endDelivery();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        bundleContext.ungetService(orderServiceReference);
        bundleContext.ungetService(deliveryProducerReference);
        System.out.println("🚪 DeliveryConsumer Stopped.");
    }
}
