package com.inventory.service;

//We create the service and EXPORT it.

//Producer implements this
public interface IngredientUsageService {
	
	void useIngredient(String ingredient,int quantity);

}
