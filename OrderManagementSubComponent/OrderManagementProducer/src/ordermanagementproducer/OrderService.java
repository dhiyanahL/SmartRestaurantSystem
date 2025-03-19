package ordermanagementproducer;

import java.util.List;

public interface OrderService {
	
	void createDineInOrder(DineInOrder dineInOrder);
	void createDeliveryOrder(DeliveryOrder deleDeliveryOrder);
    List<DineInOrder> getDineInOrders();
    List<DeliveryOrder> getDeliveryOrders();
    DineInOrder getOrderByDineInId(int id);
    DeliveryOrder getOrderByDeliveryId(int id);
    boolean updateDineInOrder(int id, DineInOrder updatedOrder);
    boolean updateDeliveryOrder(int id, DeliveryOrder updaDeliveryOrder);
    boolean deleteDineInOrder(int id);
    boolean deleteDeliveryOrder(int id);

}
