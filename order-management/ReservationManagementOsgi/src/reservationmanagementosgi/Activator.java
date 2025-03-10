package reservationmanagementosgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {

	private static BundleContext context;
	private ReservationService reservationService;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Hi, Reservation Service Started");
		reservationService = new ReservationServiceImpl();
		bundleContext.registerService(ReservationService.class.getName(), reservationService, null);
		System.out.println("Reservation Service Registered");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		 System.out.println("Hello, OSGi Bundle Stopped!");

	        // Unregister the OrderService
	        if (reservationService != null) {
	            bundleContext.ungetService(bundleContext.getServiceReference(ReservationService.class.getName()));
	        }

	        System.out.println("OrderService unregistered.");
		
	}

}
