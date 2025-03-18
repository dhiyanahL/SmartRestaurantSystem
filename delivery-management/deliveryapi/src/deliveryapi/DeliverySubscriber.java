package deliveryapi;

import ordermanagementproducer.DeliveryOrder;

public interface DeliverySubscriber {
	
	void onDeliveryCompleted(DeliveryOrder deliveryOrder);

}
