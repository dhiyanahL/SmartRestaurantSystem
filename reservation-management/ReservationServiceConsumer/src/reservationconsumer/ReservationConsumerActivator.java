package reservationconsumer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import reservationprovider.ReservationService;

public class ReservationConsumerActivator implements BundleActivator {
    
    private Scanner scanner = new Scanner(System.in);
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10}"); 
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    public void start(BundleContext context) throws Exception {
        ServiceReference<?> reference = context.getServiceReference(ReservationService.class.getName());
        if (reference == null) {
            System.out.println("Reservation Service Not Found!");
            return;
        }
        
        ReservationService reservationService = (ReservationService) context.getService(reference);
        
        while (true) {
            int choice = -1;
            while (true) {
                try {
                    System.out.println("\n=============================================");
                    System.out.println("🔹 What would you like to do?");
                    System.out.println("=============================================");
                    System.out.println("1️⃣ Create a Reservation");
                    System.out.println("2️⃣ Update a Reservation");
                    System.out.println("3️⃣ Cancel a Reservation");
                    System.out.println("4️⃣ Check a Reservation");
                    System.out.println("5️⃣ View All Reservations"); 
                    System.out.println("6️⃣ Exit");
                    System.out.println("=============================================");
                    System.out.print("Enter choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (choice >= 1 && choice <= 6) break;
                    System.out.println("Invalid choice! Please enter a number between 1-6."); 
                } catch (InputMismatchException e) { 
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine();
                }
            }

            if (choice == 6) break;

            if (choice == 5) { 
                List<reservationprovider.Reservation> allReservations = reservationService.getAllReservations();
                if (allReservations.isEmpty()) {
                    System.out.println("No reservations available.");
                } else {
                    System.out.println("\n=============================================");
                    System.out.println("Current Reservations:");
                    System.out.println("=============================================");
                    for (reservationprovider.Reservation r : allReservations) {
                        System.out.println(r);
                    }
                }
                continue;
            }

            System.out.print("Enter your name: ");
            String name = scanner.nextLine().trim();

            String phone;
            while (true) {
                System.out.print("Enter your phone number (10 digits): ");
                phone = scanner.nextLine().trim();
                if (PHONE_PATTERN.matcher(phone).matches()) {
                    break;
                }
                System.out.println("Invalid phone number! It must be exactly 10 digits.");
            }

            if (choice == 1) { 
                int seats = getValidSeats();
                String date = getValidDate();
                String meal = getValidMeal();

                int tableAssigned = reservationService.makeReservation(name, phone, seats, date, meal);
                
                if (tableAssigned > 0 && tableAssigned < 1000) {
                    System.out.println("\nReservation Successful!");
                    System.out.println("=============================================");
                    System.out.println("Name: " + name);
                    System.out.println("Phone: " + phone);
                    System.out.println("Table: " + tableAssigned);
                    System.out.println("Seats: " + seats);
                    System.out.println("Date: " + date);
                    System.out.println("Meal: " + meal);
                    System.out.println("=============================================");
                } else if (tableAssigned >= 1000) {
                    System.out.println("\nReservation Successful! Your seating is split across combined tables.");
                    System.out.println("=============================================");
                    System.out.println("Name: " + name);
                    System.out.println("Phone: " + phone);
                    System.out.println("Combined Tables Assigned.");
                    System.out.println("Seats: " + seats);
                    System.out.println("Date: " + date);
                    System.out.println("Meal: " + meal);
                    System.out.println("=============================================");
                } else {
                    System.out.println("No available table.");
                }
                
            } else if (choice == 2) { 
                int seats = getValidSeats();
                String date = getValidDate();
                String meal = getValidMeal();
                boolean updated = reservationService.updateReservation(name, phone, seats, date, meal);
                
                if (updated) {
                    System.out.println("Reservation updated!");
                    System.out.println(reservationService.checkReservation(name, phone));
                } else {
                    System.out.println("Update failed.");
                }
                
            } else if (choice == 3) {  
                String reservationDetails = reservationService.checkReservation(name, phone);
                
                if (reservationDetails.equals("No reservation found.")) {
                    System.out.println(reservationDetails);
                } else {
                    System.out.println("\nYour current reservation:");
                    System.out.println(reservationDetails);
                    System.out.print("\nAre you sure you want to cancel your reservation? (Y/N): ");
                    
                    String confirmation = scanner.nextLine().trim();
                    if (confirmation.equalsIgnoreCase("Y")) {
                        boolean cancelled = reservationService.cancelReservation(name, phone);
                        System.out.println(cancelled ? "Reservation canceled!" : "Cancellation failed.");
                    } else {
                        System.out.println("Cancellation aborted.");
                    }
                }
                
            } else if (choice == 4) { 
                System.out.println(reservationService.checkReservation(name, phone));
            }
        }
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Reservation Consumer Stopped.");
    }

    private int getValidSeats() {
        int seats = 0;
        while (true) {
            try {
                System.out.print("Enter number of seats (1-8): ");
                seats = scanner.nextInt();
                scanner.nextLine();
                if (seats >= 1 && seats <= 8) {
                    break;
                }
                System.out.println("Invalid seat count! Enter a number between 1 and 8.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
        return seats;
    }

    private String getValidDate() {
        String date;
        while (true) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            date = scanner.nextLine().trim();
            if (DATE_PATTERN.matcher(date).matches()) {
                break;
            }
            System.out.println("Invalid date format! Please enter in YYYY-MM-DD format.");
        }
        return date;
    }

    private String getValidMeal() {
        String meal;
        while (true) {
            System.out.print("Enter meal (breakfast/lunch/dinner): ");
            meal = scanner.nextLine().trim().toLowerCase();
            if (meal.equals("breakfast") || meal.equals("lunch") || meal.equals("dinner")) {
                break;
            }
            System.out.println("Invalid meal type! Choose from 'breakfast', 'lunch', or 'dinner'.");
        }
        return meal;
    }
}
