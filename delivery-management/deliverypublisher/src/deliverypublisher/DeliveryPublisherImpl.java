package deliveryproducer;

import java.util.ArrayList;
import java.util.List;
import ordermanagementproducer.DeliveryOrder;

public class DeliveryProducerImpl implements DeliveryProducer {

    private List<DeliveryOrder> deliveryOrders = new ArrayList<>();
    private String deliveryPerson;
    
    public DeliveryProducerImpl() {
    	 // Hardcoded delivery orders
        deliveryOrders.add(new DeliveryOrder(1, "Pizza", 2, "John Doe", "0771234567", "123 Main St, Cityville"));
        deliveryOrders.add(new DeliveryOrder(2, "Burger", 3, "Jane Doe", "0777654321", "456 Another St, Townville"));
    }

    @Override
    public void addDeliveryOrder(DeliveryOrder deliveryOrder) {
        if (deliveryOrder == null) {
            System.out.println("❌ Error: Delivery order is null.");
            return;
        }

        // Add the delivery order to the list
        deliveryOrders.add(deliveryOrder);
        System.out.println("📦 Delivery Order Added: " + deliveryOrder);
    }

    @Override
    public void assignDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        System.out.println("🚴 Delivery Person Assigned: " + deliveryPerson);
    }

    @Override
    public double calculateDeliveryCharges(int totalQuantity) {
        // Example: $2 per item
        return totalQuantity * 2.0;
    }

    @Override
    public int calculateDeliveryTime(int totalQuantity) {
        // Example: 10 minutes per item
        return totalQuantity * 10;
    }

    @Override
    public void startDelivery() {
    	if(deliveryPerson == null) {
    		System.out.println("❌ No delivery person assigned");
    		return;
    	}
    	
        System.out.println("🚚 Delivery Started by " + deliveryPerson);
        try {
            // Simulate delivery time
            Thread.sleep(5000); // 5 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endDelivery() {
    	 if (deliveryPerson == null) {
             System.out.println("❌ No delivery person assigned.");
             return;
         }
        System.out.println("✅ Delivery Completed by " + deliveryPerson);
    }

    @Override
    public void generateDeliveryReport() {
        System.out.println("\n=== Delivery Report ===");
        System.out.println("Delivery Person: " + deliveryPerson);
        int totalQuantity = deliveryOrders.stream().mapToInt(DeliveryOrder::getQuantity).sum();
        System.out.println("Total Quantity: " + totalQuantity);
        System.out.println("Delivery Charges: $" + calculateDeliveryCharges(totalQuantity));
        System.out.println("Estimated Delivery Time: " + calculateDeliveryTime(totalQuantity) + " minutes");
    }
}
