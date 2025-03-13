package reservationmanagementosgi;

public class Table {
	
	 int tableNumber;
	    int seatingCapacity;

	    public Table(int tableNumber, int seatingCapacity) {
	        this.tableNumber = tableNumber;
	        this.seatingCapacity = seatingCapacity;
	    }

		public int getTableNumber() {
			
			return tableNumber;
		}

		public int getSeatingCapacity() {
			return seatingCapacity;
		}

}
