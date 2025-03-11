package reservationprovider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ReservationActivator implements BundleActivator {
    
	private static BundleContext context;
	private ReservationService reservationService;
	
	  static BundleContext getContext() {
	        return context;
	    }


    public void start(BundleContext bundleContext) throws Exception {
       
       
        System.out.println("Reservation Service Started!");
        context = bundleContext;
        reservationService = new ReservationServiceImpl();
        bundleContext.registerService(ReservationService.class.getName(), reservationService, null);
    }

    public void stop(BundleContext bundleContext) throws Exception {
       
        System.out.println("Reservation Service Stopped!");
        if(reservationService != null) {
        	
        	bundleContext.ungetService(bundleContext.getServiceReference(ReservationService.class.getName()));
        	
        }
        
        System.out.println("OrderService unregistered.");
    }
}
