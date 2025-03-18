package reservationprovider;

import java.util.List;

public interface ReservationService {

	int makeReservation(String customerName, String phone, int requiredSeats, String date, String meal);
	boolean cancelReservation(String customerName, String phone);
	String checkReservation(String customerName, String phone);
	boolean updateReservation(String customerName, String phone, int newSeats, String newDate, String newMeal);
	List<Reservation> getAllReservations();
	
	Reservation getSampleReservation();
}
