package deliverysubscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import deliveryapi.DeliverySubscriber;
import deliveryapi.DeliveryPublisher;

public class DeliverySubscriberActivator implements BundleActivator {

    private ServiceRegistration<DeliverySubscriber> serviceRegistration;
    private ServiceTracker<DeliveryPublisher, DeliveryPublisher> deliveryPublisherTracker;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("DeliverySubscriber Service Starting...");

        // Initialize the ServiceTracker for DeliveryPublisher
        deliveryPublisherTracker = new ServiceTracker<DeliveryPublisher, DeliveryPublisher>(context, DeliveryPublisher.class, null) {
            @Override
            public DeliveryPublisher addingService(ServiceReference<DeliveryPublisher> reference) {
                // When the DeliveryPublisher service is available, start the DeliverySubscriber
                System.out.println("✅ DeliveryPublisher found, registering DeliverySubscriber.");
                DeliveryPublisher deliveryPublisher = context.getService(reference);

                // Register the DeliverySubscriber service after DeliveryPublisher is available
                DeliverySubscriberImpl deliverySubscriber = new DeliverySubscriberImpl();
                serviceRegistration = context.registerService(DeliverySubscriber.class, deliverySubscriber, null);
                System.out.println("✅ DeliverySubscriber Service Registered.");
                
                return super.addingService(reference); // Continue tracking the service
            }

            @Override
            public void removedService(ServiceReference<DeliveryPublisher> reference, DeliveryPublisher service) {
                // Unregister DeliverySubscriber when DeliveryPublisher is no longer available
                if (serviceRegistration != null) {
                    serviceRegistration.unregister();
                    System.out.println("📢 DeliverySubscriber Service Unregistered.");
                }
                super.removedService(reference, service);
            }
        };

        // Start tracking the DeliveryPublisher service
        deliveryPublisherTracker.open();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping DeliverySubscriber Service...");

        // Unregister the service if necessary
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
            System.out.println("📢 DeliverySubscriber Service Unregistered.");
        }

        // Close the ServiceTracker when the bundle is stopped
        deliveryPublisherTracker.close();
    }
}
