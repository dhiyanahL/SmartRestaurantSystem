package com.inventory.service;

import java.util.HashMap;
import java.util.Map;

public class IngredientUsageServiceImp implements IngredientUsageService{

	private Map<String, Integer> ingredientStock = new HashMap<>();
	
	public IngredientUsageServiceImp() {
		//Initial stock levels
		ingredientStock.put("Tomato", 100);
		ingredientStock.put("Cheese", 50);
		ingredientStock.put("Dough", 30);
		}
	
	@Override
	public void useIngredient(String ingredient, int quantity) {
		
		if(ingredientStock.containsKey(ingredient)) {
			
			int currentStock = ingredientStock.get(ingredient);
			
			if(currentStock >= quantity) {
				
				ingredientStock.put(ingredient,  currentStock - quantity);
				System.out.println("Used "+ quantity + " of " + ingredient + ". Remaining : " + ingredientStock.get(ingredient));
				
			} else {
				System.out.println("Not enough stock for " + ingredient);
			}
		}else {
			System.out.println("Ingredient not found : " +ingredient);
		}
		
	}
	

}
