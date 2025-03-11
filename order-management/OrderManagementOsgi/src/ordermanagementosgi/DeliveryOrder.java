package ordermanagementosgi;

public class DeliveryOrder {
	
	private int deliveryOrderId;
	private String type;
	private int quantity;
	private String customerName;
	private String phone;
	private String address;
	
	
	public DeliveryOrder(int deliveryOrderId, String type, int quantity, String customerName, String phone,
			String address) {
		super();
		this.deliveryOrderId = deliveryOrderId;
		this.type = type;
		this.quantity = quantity;
		this.customerName = customerName;
		this.phone = phone;
		this.address = address;
	}


	public int getDeliveryOrderId() {
		return deliveryOrderId;
	}


	public void setDeliveryOrderId(int deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
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


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String toString() {
        return "Order Details: " +
               "\nID: " + deliveryOrderId +
               "\nItem Name: " + type +
               "\nQuantity: " + quantity +
               "\nCustomer Name: " + customerName +
               "\nContact No: " + phone +
               "\nDelivery Address: " + address;
    }
	
	

}
