package reservationmanagementosgi;

import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {
	
	private List<Reservation> reservations = new ArrayList<>();
	private List<Table> tables = new ArrayList<>();
	
	
	public void manageTables() {
		
		tables.add(new Table(1, 2));
        tables.add(new Table(2, 2));
        tables.add(new Table(3, 4));
        tables.add(new Table(4, 6));
        tables.add(new Table(5, 8));
        tables.add(new Table(6, 1));
        tables.add(new Table(7, 1));
        tables.add(new Table(8, 3));
        tables.add(new Table(9, 3));
        tables.add(new Table(10, 3));
		
	}

	@Override
	public int makeReservation(String customerName, String phone, int requiredSeats, String date, String meal,
	        int quantity) {

	    System.out.println("Searching for a table for " + requiredSeats + " seats on " + date + " (" + meal + ")");

	    // Check if a single table can fit
	    for (Table table : tables) {
	        System.out.println("Checking table " + table.getTableNumber() + " with capacity " + table.getSeatingCapacity());

	        if (table.getSeatingCapacity() >= requiredSeats && !isTableBooked(table.getTableNumber(), date, meal)) {
	            System.out.println("Table " + table.getTableNumber() + " is available. Booking it.");
	            reservations.add(new Reservation(customerName, phone, table.getTableNumber(), requiredSeats, date, meal));
	            return table.getTableNumber();
	        }
	    }

	    // Check for combined tables
	    for (Table table1 : tables) {
	        for (Table table2 : tables) {
	            if (table1 != table2 && table1.getSeatingCapacity() + table2.getSeatingCapacity() >= requiredSeats
	                && !isTableBooked(table1.getTableNumber(), date, meal)
	                && !isTableBooked(table2.getTableNumber(), date, meal)) {

	                System.out.println("Combining tables " + table1.getTableNumber() + " and " + table2.getTableNumber());
	                reservations.add(new Reservation(customerName, phone, table1.getTableNumber(), table1.getSeatingCapacity(), date, meal));
	                reservations.add(new Reservation(customerName, phone, table2.getTableNumber(), table2.getSeatingCapacity(), date, meal));

	                return 1000 + table1.getTableNumber() + table2.getTableNumber();
	            }
	        }
	    }

	    System.out.println("No available tables.");
	    return -1;
	}

	

	private boolean isTableBooked(int tableNumber, String date, String meal) {
	    for (Reservation r : reservations) {
	        if (r.getTableNumber() == tableNumber && r.getDate().equals(date) && r.getMeal().equalsIgnoreCase(meal)) {
	            System.out.println("Table " + tableNumber + " is already booked on " + date + " (" + meal + ")");
	            return true;
	        }
	    }
	    return false;
	}

	@Override
	public boolean cancelReservation(String customerName, String phone) {
        for (Reservation r : reservations) {
            if (r.getCustomerName().equals(customerName) && r.getPhone().equals(phone)) {
                reservations.remove(r); 
                return true;
            }
        }
        return false;
	}

	@Override
	public String checkReservation(String customerName, String phone) {
		for (Reservation r : reservations) {
            if (r.getCustomerName().equals(customerName) && r.getPhone().equals(phone)) {
                return r.toString();
            }
        }
        return "No reservation found.";
	}

	@Override
	public boolean updateReservation(String customerName, String phone, int newSeats, String newDate, String newMeal) {
		 Reservation existingReservation = null;

	        for (Reservation r : reservations) {
	            if (r.getCustomerName().equals(customerName) && r.getPhone().equals(phone)) {
	                existingReservation = r;
	                break;
	            }
	        }

	        if (existingReservation == null) {
	            System.out.println("No reservation found for this customer.");
	            return false;
	        }

	        for (Table table : tables) {
	            if (table.getSeatingCapacity() >= newSeats && !isTableBooked(table.getTableNumber(), newDate, newMeal)) {
	                reservations.remove(existingReservation);

	                Reservation updatedReservation = new Reservation(customerName, phone, table.getTableNumber(), newSeats, newDate, newMeal);
	                reservations.add(updatedReservation);

	                //System.out.println("Reservation successfully updated: " + updatedReservation);
	                return true;
	            }
	        }

	        System.out.println("No available table for the updated details.");
	        return false;
	}

	@Override
	public List<Reservation> getAllReservations() {
		 return reservations;
	}

	

}
