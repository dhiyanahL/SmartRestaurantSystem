package ingredientusageservice_producer;

import java.util.HashMap;
import java.util.Map;

public class IngredientUsageServiceImp implements IngredientUsageService {
	
	
	private Map<String, Integer> ingredientStock = new HashMap<>();
	
	// Constructor to initialize some hardcoded ingredient stock
    public IngredientUsageServiceImp() {
        initializeStock();
    }

    private void initializeStock() {
        // Hardcoded inventory items with initial quantities
        ingredientStock.put("Pasta", 100); // 100 units of Pasta
        ingredientStock.put("Tomato Sauce", 50); // 50 units of Tomato Sauce
        ingredientStock.put("Cheese", 30); // 30 units of Cheese
        ingredientStock.put("Olives", 20); // 20 units of Olives
        ingredientStock.put("Chicken", 60); // 60 units of Chicken
        ingredientStock.put("Garlic", 80); // 80 units of Garlic
        ingredientStock.put("Spinach", 40); // 40 units of Spinach

        System.out.println("Ingredient stock initialized.");
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
