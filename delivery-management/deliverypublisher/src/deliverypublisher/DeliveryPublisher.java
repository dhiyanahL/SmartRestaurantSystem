// DeliveryPublisher.java (Interface)
package deliverypublisher;

import ordermanagementproducer.DeliveryOrder;
import java.util.List;

public interface DeliveryPublisher {
	void assignDeliveryPerson(String deliveryPerson);
    double calculateDeliveryCharges(int totalQuantity);
    int calculateDeliveryTime(int totalQuantity);
    void startDelivery();
    void endDelivery();
    void generateDeliveryReport();
    void addDeliveryOrder(DeliveryOrder deliveryOrder); 
}
