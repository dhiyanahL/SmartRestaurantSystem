package reservationprovider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ReservationActivator implements BundleActivator {
    private ServiceRegistration<?> serviceRegistration;

    public void start(BundleContext context) throws Exception {
        ReservationService service = new ReservationServiceImpl();
        serviceRegistration = context.registerService(ReservationService.class.getName(), service, null);
        System.out.println("Reservation Service Started!");
    }

    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("Reservation Service Stopped!");
    }
}
