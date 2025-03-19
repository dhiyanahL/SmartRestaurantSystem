package deliveryconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import deliveryproducer.DeliveryProducer;

public class DeliveryConsumerActivator implements BundleActivator {

    private ServiceReference<?> deliveryProducerReference;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("🔍 Searching for DeliveryProducer...");

        // Get DeliveryProducer
        deliveryProducerReference = bundleContext.getServiceReference(DeliveryProducer.class.getName());
        if (deliveryProducerReference == null) {
            System.out.println("❌ DeliveryProducer Not Found.");
            return;
        }
        DeliveryProducer deliveryProducer = (DeliveryProducer) bundleContext.getService(deliveryProducerReference);

        System.out.println("✅ DeliveryProducer Found.");

        // Start Delivery Process
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Delivery Person Name: ");
        String deliveryPerson = scanner.nextLine();
        deliveryProducer.assignDeliveryPerson(deliveryPerson);

        // Generate Report and Start Delivery
        deliveryProducer.generateDeliveryReport();
        deliveryProducer.startDelivery();
        deliveryProducer.endDelivery();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {       
        try {
            
            if (deliveryProducerReference != null) {
                bundleContext.ungetService(deliveryProducerReference);
                System.out.println("✅ DeliveryProducer Unregistered.");
            }

            System.out.println("🚪 DeliveryConsumer Stopped.");
        } catch (Exception e) {
            // Log the error but don't let it crash the system
            System.err.println("❌ Error while stopping DeliveryConsumer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
