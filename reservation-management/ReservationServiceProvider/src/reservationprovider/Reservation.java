package reservationprovider;

public class Reservation {
    private String customerName;
    private String phone;
    private int tableNumber;
    private int requiredSeats;
    private String date;
    private String meal;

    public Reservation(String customerName, String phone, int tableNumber, int requiredSeats, String date, String meal) {
        this.customerName = customerName;
        this.phone = phone;
        this.tableNumber = tableNumber;
        this.requiredSeats = requiredSeats;
        this.date = date;
        this.meal = meal;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getRequiredSeats() {
        return requiredSeats;
    }

    public String getDate() {
        return date;
    }

    public String getMeal() {
        return meal;
    }

    @Override
    public String toString() {
        return "Reservation for " + customerName + " at Table " + tableNumber + 
               " [Seats: " + requiredSeats + ", " + date + " " + meal + "]";
    }
}

