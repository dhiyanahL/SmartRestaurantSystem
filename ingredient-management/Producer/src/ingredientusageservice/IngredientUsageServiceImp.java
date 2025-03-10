package ingredientusageservice;

import java.util.HashMap;
import java.util.Map;

public class IngredientUsageServiceImp implements IngredientUsageService {

	private Map<String, Integer> ingredientStock = new HashMap<>(); // Stores ingredient quantities
    private Map<String, Map<String, Integer>> dishIngredients = new HashMap<>(); // Dish-to-ingredients mapping

    public IngredientUsageServiceImp() {
    	initializeStock();
        initializeDishIngredients();
    }
	
    // Define dishes and required ingredients
    private void initializeDishIngredients() {
        Map<String, Integer> pastaIngredients = new HashMap<>();
        pastaIngredients.put("Pasta", 1);
        pastaIngredients.put("Tomato Sauce", 2);
        pastaIngredients.put("Cheese", 1);

        Map<String, Integer> chickenPizzaIngredients = new HashMap<>();
        chickenPizzaIngredients.put("Chicken", 2);
        chickenPizzaIngredients.put("Cheese", 2);
        chickenPizzaIngredients.put("Garlic", 1);

        dishIngredients.put("Pasta", pastaIngredients);
        dishIngredients.put("Chicken Pizza", chickenPizzaIngredients);
    }

    
    @Override
	public void initializeStock() {
    	ingredientStock.put("Pasta", 10);
        ingredientStock.put("Tomato Sauce", 10);
        ingredientStock.put("Cheese", 10);
        ingredientStock.put("Chicken", 10);
        ingredientStock.put("Garlic", 10);
		
	}
    
    @Override
	public boolean checkIngredientsForDish(String dishName) {
    	if (!dishIngredients.containsKey(dishName)) {
            System.out.println("❌ Dish not found: " + dishName);
            return false;
        }

        Map<String, Integer> requiredIngredients = dishIngredients.get(dishName);

        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();
            int availableQuantity = ingredientStock.getOrDefault(ingredient, 0);

            if (availableQuantity < requiredQuantity) {
                System.out.println("❌ Not enough " + ingredient + " for " + dishName);
                return false;
            }
        }

        return true;
  
	}
 // Deduct ingredients when an order is placed
    @Override
	public void useIngredientsForDish(String dishName) {
    	if (!dishIngredients.containsKey(dishName)) return;

        Map<String, Integer> requiredIngredients = dishIngredients.get(dishName);

        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();

            ingredientStock.put(ingredient, ingredientStock.get(ingredient) - requiredQuantity);
        }
        System.out.println("✅ Ingredients deducted for " + dishName);
	}
    
	@Override
	public void useIngredient(String ingredient, int quantity) {
		ingredientStock.put(ingredient, ingredientStock.getOrDefault(ingredient, 0) - quantity);
	    
	}

	@Override
	public void addIngredient(String ingredient, int quantity) {
	     ingredientStock.put(ingredient, ingredientStock.getOrDefault(ingredient, 0) + quantity);
	     
	}

	@Override
	public void updateIngredient(String ingredient, int quantity) {
	      ingredientStock.put(ingredient, quantity);
	      
	}

	@Override
	public void deleteIngredient(String ingredient) {
	      ingredientStock.remove(ingredient);
	      
	}

	@Override
	public int getIngredient(String name) {
		return ingredientStock.getOrDefault(name, 0);
	}

	

	

	

}
