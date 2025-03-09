package ordermanagementosgi;

public class DineInOrder {
	
	private int dineInOrderId;
	private String type;
	private int quantity;
	private String customer;
	private String phone;
	private int tableNo;
	
	
	public DineInOrder(int dineInOrderId, String type, int quantity, String customer, String phone, int tableNo) {
		super();
		this.dineInOrderId = dineInOrderId;
		this.type = type;
		this.quantity = quantity;
		this.customer = customer;
		this.phone = phone;
		this.tableNo = tableNo;
	}


	public int getDineInOrderId() {
		return dineInOrderId;
	}


	public void setDineInOrderId(int dineInOrderId) {
		this.dineInOrderId = dineInOrderId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getTableNo() {
		return tableNo;
	}


	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	
	
    public String toString() {
        return "Order Details: " +
               "\nID: " + dineInOrderId +
               "\nItem Name: " + type +
               "\nQuantity: " + quantity +
               "\nCustomer Name: " + customer +
               "\nContact No: " + phone +
               "\nTable No: " + tableNo;
    }
	
	

}
