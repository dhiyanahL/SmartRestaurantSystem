package main;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import java.util.Scanner;

public class MainActivator implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        MainActivator.context = bundleContext;

        // Ensure all required bundles are resolved
        resolveBundles();

        // Display main menu to the user
        showMenu();
        
        // Handle the user input and select corresponding options
        handleUserChoice();
    }

    public void stop(BundleContext bundleContext) throws Exception {
        MainActivator.context = null;
    }

    private void showMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Reservation");
        System.out.println("2. Order");
        System.out.println("3. Inventory");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private void handleUserChoice() {
        Scanner scanner = new Scanner(System.in);

        while (true) {  // Loop to keep showing the menu until the user exits
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("You selected Reservation.");
                    startBundle("ReservationManagementOsgi");  // Start Reservation bundle
                    break;

                case 2:
                    System.out.println("You selected Order.");
                    startBundle("OrderManagementConsumer");  // Start OrderConsumer bundle
                    break;

                case 3:
                    System.out.println("You selected Inventory.");
                    startBundle("IngredientManagementOsgi");  // Start Inventory bundle
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    System.exit(0);  // Terminate the program
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    showMenu();  // Redisplay the menu
                    continue;  // Ensure we don't skip the rest of the logic after an invalid choice
            }

            // After performing the action, ask for the user's choice again
            showMenu();  // Display the menu again
        }
    }

    private void startBundle(String symbolicName) {
        System.out.println("Starting " + symbolicName + "...");

        // Start the bundle with the given symbolic name
        Bundle[] bundles = context.getBundles();
        for (Bundle bundle : bundles) {
            if (bundle.getSymbolicName().equals(symbolicName)) {
                try {
                    // Check if the bundle is already resolved
                    if (bundle.getState() != Bundle.RESOLVED) {
                        System.out.println("Resolving " + symbolicName + " bundle...");
                        bundle.start();  // Starting the bundle will resolve it if necessary
                    } else {
                        System.out.println(symbolicName + " bundle is already resolved.");
                    }
                    System.out.println(symbolicName + " bundle started.");
                    break;
                } catch (BundleException e) {
                    System.out.println("Error starting the " + symbolicName + " bundle.");
                    e.printStackTrace();
                }
            }
        }
    }

    private void resolveBundles() {
        // Iterate over all bundles and resolve them if needed
        Bundle[] bundles = context.getBundles();
        for (Bundle bundle : bundles) {
            if (bundle.getState() == Bundle.INSTALLED) {
                try {
                    System.out.println("Resolving bundle: " + bundle.getSymbolicName());
                    bundle.start();  // Start the bundle to resolve it
                } catch (BundleException e) {
                    System.out.println("Error resolving the " + bundle.getSymbolicName() + " bundle.");
                    e.printStackTrace();
                }
            }
        }
    }
}
