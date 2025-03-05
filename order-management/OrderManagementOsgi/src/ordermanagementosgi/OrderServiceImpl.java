package ordermanagementosgi;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService{
	
	
	private final List<Order> orders = new ArrayList<>();
	private int nextOrderId = 1;

	@Override
    public void createOrder(Order order) {
        order.setId(nextOrderId++);
        orders.add(order);
        System.out.println("🆕 Order Created: " + order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public Order getOrderById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean updateOrder(int id, Order updatedOrder) {
        for (Order order : orders) {
            if (order.getId() == id) {
                order.setItemName(updatedOrder.getItemName());
                order.setQuantity(updatedOrder.getQuantity());
                System.out.println("✏️ Order Updated: " + order);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteOrder(int id) {
        return orders.removeIf(order -> order.getId() == id);
    }
	

}
