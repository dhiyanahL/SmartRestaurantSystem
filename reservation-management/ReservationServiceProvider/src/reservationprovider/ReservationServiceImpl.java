package reservationprovider;

import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    
    private List<Reservation> reservations = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();
    
    public ReservationServiceImpl() {
        tables.add(new Table(1, 2));
        tables.add(new Table(2, 2));
        tables.add(new Table(3, 4));
        tables.add(new Table(4, 6));
        tables.add(new Table(5, 8));
    }

    @Override
    public int makeReservation(String customerName, String phone, int requiredSeats, String date, String meal) {
        for (Table table : tables) {
            if (table.seatingCapacity >= requiredSeats && !isTableBooked(table.getTableNumber(), date, meal)) {
                Reservation reservation = new Reservation(customerName, phone, table.getTableNumber(), requiredSeats, date, meal);
                reservations.add(reservation);
                return table.getTableNumber();
            }
        }
        return -1;
    }

    private boolean isTableBooked(int tableNumber, String date, String meal) {
        for (Reservation r : reservations) {
            if (r.getTableNumber() == tableNumber && r.getDate().equals(date) && r.getMeal().equalsIgnoreCase(meal)) {
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
