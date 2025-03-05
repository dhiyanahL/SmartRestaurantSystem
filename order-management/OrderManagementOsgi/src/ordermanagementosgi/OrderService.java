package ordermanagementosgi;

import java.util.List;

public interface OrderService {
	
	void createOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(int id);
    boolean updateOrder(int id, Order updatedOrder);
    boolean deleteOrder(int id);

}
