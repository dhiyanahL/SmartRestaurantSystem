package deliverypublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import ordermanagementproducer.OrderService;

import org.osgi.framework.ServiceReference;

public class DeliveryPublisherActivator implements BundleActivator {

    private ServiceRegistration<?> serviceRegistration;
    private static boolean publisherReady = false;  // Flag to signal when publisher is ready

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("DeliveryPublisher Service Starting...");
        
        ServiceReference<OrderService> serviceReference = null;

        // Wait for the OrderService to be available
        while (serviceReference == null) {
            serviceReference = context.getServiceReference(OrderService.class);
            if (serviceReference == null) {
                System.err.println("⚠️ Waiting for OrderService to be available.");
                Thread.sleep(2000);  // Wait for 2 seconds before retrying
            }
        }

        // Once OrderService is found, get the service instance
        OrderService orderService = context.getService(serviceReference);

        // Create an instance of DeliveryPublisherImpl with OrderService
        DeliveryPublisherImpl deliveryPublisher = new DeliveryPublisherImpl();

        // Register the DeliveryPublisher service
        serviceRegistration = context.registerService(new String[] {DeliveryPublisher.class.getName()}, deliveryPublisher, null);

        // Signal that the publisher is ready
        publisherReady = true;
        System.out.println("✅ DeliveryPublisher Service Registered.");
        
        // Notify DeliverySubscriber to start (you can also implement this in a more sophisticated way if necessary)
        notifySubscriberToStart(context);
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

    // This method can be used to notify the subscriber to start once the publisher is ready.
    private void notifySubscriberToStart(BundleContext context) {
        // Example logic: check the publisherReady flag and then trigger the subscriber registration
        if (publisherReady) {
            System.out.println("DeliveryPublisher is ready, notifying DeliverySubscriber to start.");

            // Here, we assume the DeliverySubscriber registration is done by another bundle
            // that listens for the readiness flag and initiates the registration accordingly
        }
    }

    // A method to get the status of the publisher's readiness
    public static boolean isPublisherReady() {
        return publisherReady;
    }
}
