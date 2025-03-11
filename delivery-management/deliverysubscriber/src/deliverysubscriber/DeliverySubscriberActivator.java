package deliverysubscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class DeliverySubscriberActivator implements BundleActivator {

    private ServiceRegistration<DeliverySubscriber> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("DeliverySubscriber Service Starting...");

        // Register the DeliverySubscriber service
        DeliverySubscriberImpl deliverySubscriber = new DeliverySubscriberImpl();
        serviceRegistration = context.registerService(DeliverySubscriber.class, deliverySubscriber, null);
        
        System.out.println("DeliverySubscriber Service Registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping DeliverySubscriber Service...");

        // Unregister the service
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
            System.out.println("📢 DeliverySubscriber Service Unregistered.");
        }
    }
}
