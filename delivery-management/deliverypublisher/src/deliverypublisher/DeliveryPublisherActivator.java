package deliverypublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import ordermanagementproducer.OrderService;

import org.osgi.framework.ServiceReference;

public class DeliveryPublisherActivator implements BundleActivator {

	private ServiceRegistration<DeliveryPublisher> registration;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("🚀 Starting DeliveryProducer...");

        // Check if OrderService is available before starting DeliveryProducer
        ServiceReference<OrderService> orderServiceReference = bundleContext.getServiceReference(OrderService.class);
        if (orderServiceReference != null) {
            System.out.println("✅ OrderService Found. Registering DeliveryProducer.");

            // Register the DeliveryProducer service
            DeliveryPublisher deliveryProducer = new DeliveryPublisherImpl();
            registration = bundleContext.registerService(DeliveryPublisher.class, deliveryProducer, null);
            System.out.println("✅ DeliveryProducer Service Registered.");
        } else {
            System.out.println("❌ OrderService Not Found. DeliveryProducer not started.");
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("🛑 Stopping DeliveryProducer...");
        // Unregister the service
        if (registration != null) {
            registration.unregister();
        }
        System.out.println("✅ DeliveryProducer Service Unregistered.");
    }
}
