package ordermanagementproducer;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService{

	
	private final List<DineInOrder> dineInOrders = new ArrayList<>();
	private final List<DeliveryOrder> deliveryOrders = new ArrayList<>();
	
	private int nextOrderId = 1;

	@Override
    public void createDineInOrder(DineInOrder dineInOrder) {
        dineInOrder.setDineInOrderId(nextOrderId++);
        dineInOrders.add(dineInOrder);
        System.out.println("🆕 Order Created: " + dineInOrder);
    }

	@Override
	public void createDeliveryOrder(DeliveryOrder deliveryOrder) {
		
		deliveryOrder.setDeliveryOrderId(nextOrderId++);
		deliveryOrders.add(deliveryOrder);
		System.out.println("🚗 Delivery Order Created : " + deliveryOrder );
		
	}

	@Override
	public List<DineInOrder> getDineInOrders() {
		
		return dineInOrders;
	}

	@Override
	public List<DeliveryOrder> getDeliveryOrders() {
		
		return deliveryOrders;
	}

	@Override
	public DineInOrder getOrderByDineInId(int id) {
		
		 return dineInOrders.stream().filter(dineInOrder -> dineInOrder.getDineInOrderId() == id).findFirst().orElse(null);
	}

	@Override
	public DeliveryOrder getOrderByDeliveryId(int id) {
		
		return deliveryOrders.stream().filter(deliveryOrder -> deliveryOrder.getDeliveryOrderId() == id).findFirst().orElse(null);
	}

	@Override
	public boolean updateDineInOrder(int id, DineInOrder updatedOrder) {
		for (int i = 0; i < dineInOrders.size(); i++) {
            if (dineInOrders.get(i).getDineInOrderId() == id) {
                dineInOrders.set(i, updatedOrder);
                updatedOrder.setDineInOrderId(id);
                return true;
            }
        }
        return false;
	}

	@Override
	public boolean updateDeliveryOrder(int id, DeliveryOrder updatedDeliveryOrder) {
		 for (int i = 0; i < deliveryOrders.size(); i++) {
	            if (deliveryOrders.get(i).getDeliveryOrderId() == id) {
	                deliveryOrders.set(i, updatedDeliveryOrder);
	                updatedDeliveryOrder.setDeliveryOrderId(id);
	                return true;
	            }
	        }
	        return false;
	}

	@Override
	public boolean deleteDineInOrder(int id) {
		return dineInOrders.removeIf(order -> order.getDineInOrderId() == id);
	}

	@Override
	public boolean deleteDeliveryOrder(int id) {
		return deliveryOrders.removeIf(order -> order.getDeliveryOrderId() == id);
	}

   

}
