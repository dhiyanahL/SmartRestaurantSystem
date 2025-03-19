package deliveryproducer;

import ordermanagementproducer.DeliveryOrder;

public interface DeliveryProducer {
    void assignDeliveryPerson(String deliveryPerson);
    double calculateDeliveryCharges(int totalQuantity);
    int calculateDeliveryTime(int totalQuantity);
    void startDelivery();
    void endDelivery();
    void generateDeliveryReport();
    void addDeliveryOrder(DeliveryOrder deliveryOrder); 
    
}
