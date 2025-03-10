package restockservice;

import ingredientusageservice.IngredientUsageService;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class RestockService implements BundleActivator {

	  private ServiceReference<?> ingredientServiceRef;
	  private IngredientUsageService ingredientUsageService;
	  Scanner scanner = new Scanner(System.in);


	public void start(BundleContext context) throws Exception {
		System.out.println("RestockService Starting...");

        // Get reference to IngredientUsageService
        ingredientServiceRef = context.getServiceReference(IngredientUsageService.class.getName());

        if (ingredientServiceRef != null) {
            ingredientUsageService = (IngredientUsageService) context.getService(ingredientServiceRef);
            System.out.println("Connected to IngredientUsageService!");

            // Initialize stock when the service starts
            ingredientUsageService.initializeStock(); 
            System.out.println("Stock initialized successfully.");

            int choice;
            do {
                System.out.println("\n****** Inventory Management Menu *****");
                System.out.println("1. Add Ingredient");
                System.out.println("2. Update Ingredient Stock");
                System.out.println("3. Use Ingredient");
                System.out.println("4. Delete Ingredient");
                System.out.println("5. Check Ingredient Stock");
                System.out.println("6. Restock Ingredient");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1: // Add ingredient
                        addIngredient();
                        break;

                    case 2: // Update ingredient stock
                        updateIngredient();
                        break;

                    case 3: // Use ingredient
                        useIngredient();
                        break;

                    case 4: // Delete ingredient
                        deleteIngredient();
                        break;

                    case 5: // Check ingredient stock
                        checkStock();
                        break;

                    case 6: // Restock ingredient
                        restockIngredient();
                        break;

                    case 7:
                        System.out.println("Exiting Inventory Management...");
                        break;

                    default:
                        System.out.println("Invalid choice! Please select again.");
                }
            } while (choice != 7);

        } else {
            System.out.println("IngredientUsageService not found! RestockService cannot proceed.");
        }
	}

	public void stop(BundleContext context) throws Exception {
		 System.out.println("RestockService Stopping...");

	        if (ingredientServiceRef != null) {
	            context.ungetService(ingredientServiceRef);
	        }
	}
	
	private void addIngredient() {
        System.out.print("Enter ingredient name: ");
        String ingredient = scanner.nextLine();

        System.out.print("Enter quantity to add: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ingredientUsageService.addIngredient(ingredient, quantity);
        System.out.println(ingredient + " added! New stock: " + ingredientUsageService.getIngredient(ingredient));
    }

    private void updateIngredient() {
        System.out.print("Enter ingredient name: ");
        String ingredient = scanner.nextLine();

        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ingredientUsageService.updateIngredient(ingredient, quantity);
        System.out.println(ingredient + " stock updated! New stock: " + ingredientUsageService.getIngredient(ingredient));
    }

    private void useIngredient() {
        System.out.print("Enter ingredient name: ");
        String ingredient = scanner.nextLine();

        System.out.print("Enter quantity to use: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ingredientUsageService.useIngredient(ingredient, quantity);
        System.out.println("Used " + quantity + " of " + ingredient + ". Remaining stock: " + ingredientUsageService.getIngredient(ingredient));
    }

    private void deleteIngredient() {
        System.out.print("Enter ingredient name to delete: ");
        String ingredient = scanner.nextLine();

        ingredientUsageService.deleteIngredient(ingredient);
        System.out.println(ingredient + " removed from inventory.");
    }

    private void checkStock() {
        System.out.print("Enter ingredient name: ");
        String ingredient = scanner.nextLine();

        int stock = ingredientUsageService.getIngredient(ingredient);
        System.out.println("Current stock of " + ingredient + ": " + stock + " units.");
    }

    private void restockIngredient() {
        System.out.print("Enter ingredient name to check: ");
        String ingredient = scanner.nextLine();

        int currentStock = ingredientUsageService.getIngredient(ingredient);

        if (currentStock < 10) { // Threshold for restocking
            System.out.println(ingredient + " is low on stock! Current stock: " + currentStock);

            System.out.print("Enter restock quantity: ");
            int restockQuantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            ingredientUsageService.addIngredient(ingredient, restockQuantity);
            System.out.println(ingredient + " restocked! New stock: " + ingredientUsageService.getIngredient(ingredient));
        } else {
            System.out.println(ingredient + " stock is sufficient. Current stock: " + currentStock);
        }
    }


}
