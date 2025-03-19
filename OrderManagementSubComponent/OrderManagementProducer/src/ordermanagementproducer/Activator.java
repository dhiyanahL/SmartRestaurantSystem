package ordermanagementproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private OrderService orderService;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Hi, OSGi Bundle Started!");
        context = bundleContext;

        // Create an instance of the OrderServiceImpl
        orderService = new OrderServiceImpl();
        
        // Register the OrderService with the OSGi framework
        bundleContext.registerService(OrderService.class.getName(), orderService, null);
        
        System.out.println("OrderService registered.");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		 System.out.println("Hello, OSGi Bundle Stopped!");

	        // Unregister the OrderService
	        if (orderService != null) {
	            bundleContext.ungetService(bundleContext.getServiceReference(OrderService.class.getName()));
	        }

	        System.out.println("OrderService unregistered.");
	}

}
