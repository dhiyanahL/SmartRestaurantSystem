package deliverysubscriber;

import ordermanagementproducer.DeliveryOrder;

public interface DeliverySubscriber {
	
	void onDeliveryCompleted(DeliveryOrder deliveryOrder);

}
