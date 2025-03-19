package deliverypublisher;

import java.util.ArrayList;
import java.util.List;

import ordermanagementproducer.DeliveryOrder;

public class DeliveryPublisherImpl implements DeliveryPublisher {
	
	 private List<DeliveryOrder> deliveryOrders = new ArrayList<>();
	    private String deliveryPerson;

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
