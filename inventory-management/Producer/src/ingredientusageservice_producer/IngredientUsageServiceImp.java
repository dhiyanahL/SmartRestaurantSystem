package ingredientusageservice_producer;

import java.util.HashMap;
import java.util.Map;

public class IngredientUsageServiceImp implements IngredientUsageService {
	
	
	private Map<String, Integer> ingredientStock = new HashMap<>();

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

	@Override
	public void addIngredient(String ingredient, int quantity) {
		 ingredientStock.put(ingredient, ingredientStock.getOrDefault(ingredient, 0) + quantity);
	     System.out.println(ingredient + " added with quantity: " + quantity);
	    
		
	}

	@Override
	public void updateIngredient(String ingredient, int quantity) {
		if (ingredientStock.containsKey(ingredient)) {
            ingredientStock.put(ingredient, quantity);
            System.out.println(ingredient + " updated to quantity: " + quantity);
        } else {
            System.out.println(ingredient + " does not exist.");
        }
	}

	@Override
	public void deleteIngredient(String ingredient) {
		 if (ingredientStock.remove(ingredient) != null) {
	            System.out.println(ingredient + " removed from inventory.");
	        } else {
	            System.out.println(ingredient + " does not exist.");
	        }
		
	}


	@Override
	public int getIngredient(String name) {
		return ingredientStock.getOrDefault(name, 0);
		
	}

}
