package main;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Bundle;

import java.util.Scanner;


public class MainActivator implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        MainActivator.context = bundleContext;

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
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        switch (choice) {
            case 1:
                System.out.println("You selected Reservation.");
                startBundle("reservationmanagementosgi");  // Start Reservation bundle
                break;

            case 2:
                System.out.println("You selected Order.");
                startBundle("ordermanagementosgiconsumer");  // Start OrderConsumer bundle
                break;

            case 3:
                System.out.println("You selected Inventory.");
                startBundle("inventorymanagementosgi");  // Start Inventory bundle
                break;

            case 4:
                System.out.println("Exiting program...");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid option. Please try again.");
                showMenu();
                handleUserChoice();
        }
    }

    private void startBundle(String symbolicName) {
        System.out.println("Starting " + symbolicName + "...");

        // Start the bundle with the given symbolic name
        Bundle[] bundles = context.getBundles();
        for (Bundle bundle : bundles) {
            if (bundle.getSymbolicName().equals(symbolicName)) {
                try {
                    bundle.start();  // Start the bundle
                    System.out.println(symbolicName + " bundle started.");
                    break;
                } catch (Exception e) {
                    System.out.println("Error starting the " + symbolicName + " bundle.");
                    e.printStackTrace();
                }
            }
        }
    }

    private void startOrderConsumer() {
        System.out.println("Starting Order Management...");

        // Start the OrderConsumer bundle when the user selects "Order"
        Bundle[] bundles = context.getBundles();
        for (Bundle bundle : bundles) {
            if (bundle.getSymbolicName().equals("ordermanagementosgiconsumer")) {
                try {
                    bundle.start();  // Start the OrderConsumer bundle
                    System.out.println("OrderConsumer bundle started.");
                    break;
                } catch (Exception e) {
                    System.out.println("Error starting the OrderConsumer bundle.");
                    e.printStackTrace();
                }
            }
        }
    }
}
