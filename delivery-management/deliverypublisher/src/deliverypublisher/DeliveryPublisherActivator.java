package deliverypublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import ordermanagementosgi.OrderService;
import org.osgi.framework.ServiceReference;

public class DeliveryPublisherActivator implements BundleActivator {

    private ServiceRegistration<DeliveryPublisher> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("DeliveryPublisher Service Starting...");

        // Get a reference to the OrderService
        ServiceReference<OrderService> serviceReference = context.getServiceReference(OrderService.class);
        if (serviceReference == null) {
            System.err.println("⚠️ OrderService not found. DeliveryPublisher cannot start.");
            return;
        }
        OrderService orderService = context.getService(serviceReference);

        // Create an instance of the DeliveryPublisherImpl with OrderService
        DeliveryPublisherImpl deliveryPublisher = new DeliveryPublisherImpl(orderService);

        // Register the DeliveryPublisher service
        serviceRegistration = context.registerService(DeliveryPublisher.class, deliveryPublisher, null);

        System.out.println("✅ DeliveryPublisher Service Registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping DeliveryPublisher Service...");

        // Unregister the service
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
            System.out.println("📢 DeliveryPublisher Service Unregistered.");
        }
    }
}
