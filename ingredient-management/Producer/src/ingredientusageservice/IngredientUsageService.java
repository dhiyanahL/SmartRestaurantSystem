package ingredientusageservice;

public interface IngredientUsageService {
	
	void useIngredient(String ingredient,int quantity);
	void addIngredient(String ingredient, int quantity);
	void updateIngredient(String ingredient, int quantity);
	void deleteIngredient(String ingredient);
	int getIngredient(String name);
	void initializeStock();
	
	boolean checkIngredientsForDish(String dishName);
    void useIngredientsForDish(String dishName);


}
