package com.inventory.consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.inventory.service.IngredientUsageService;

public class RestockService implements BundleActivator {

	private ServiceReference<?> ingredientServiceRef;
	private IngredientUsageService ingredientUsageService;
	
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("RestockService Starting...");
		
		//Get reference to IngredientUsageService
		ingredientServiceRef = context.getServiceReference(IngredientUsageService.class.getName());
		
		if(ingredientServiceRef != null) {
			ingredientUsageService = (IngredientUsageService) context.getService(ingredientServiceRef);
			System.out.println("Connected to IngredientUsageService!");
			
			//Simulate ingredient usage and check for restock
			ingredientUsageService.useIngredient("Dough", 40);
			checkAndRequestRestock("Dough");
			
			
			
		}else {
			System.out.println("IngredientUsageService not found");
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("RestockService Stopping...");
		
		if(ingredientServiceRef != null) {
			
			context.ungetService(ingredientServiceRef);
			
		}
		
	}
	
	private void checkAndRequestRestock(String ingredient) {
		System.out.println("Checking stock for "+ingredient+ "...");
		
		System.out.println("Restock request sent for "+ingredient+"!");
	}

}
