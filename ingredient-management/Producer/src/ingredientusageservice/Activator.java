package ingredientusageservice;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration<?> serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Ingredient Usage Service Starting...");
		
		//Create and register the service
		IngredientUsageService service = new IngredientUsageServiceImp();
		
		serviceRegistration = context.registerService(IngredientUsageService.class.getName(), service, null);
		
		System.out.println("IngredientUsageService Registered.");
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping IngredientUsageService...");
		
		//Unregister the service when the bundle stops
		serviceRegistration.unregister();
		
		System.out.println("IngredientUsageService Stopped.");
		
	
	}

}
