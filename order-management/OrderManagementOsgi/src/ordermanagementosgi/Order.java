package ordermanagementosgi;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String itemName;
    private int quantity;
    
    public Order(int id, String itemName, int quantity) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Order ID: " + id + ", Item: " + itemName + ", Quantity: " + quantity;
    }
}
