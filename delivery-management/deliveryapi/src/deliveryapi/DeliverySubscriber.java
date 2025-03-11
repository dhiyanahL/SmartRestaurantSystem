package deliveryapi;
import ordermanagementosgi.DeliveryOrder;

public interface DeliverySubscriber {
	
	void onDeliveryCompleted(DeliveryOrder deliveryOrder);

}
