package com.order.service;

import java.util.List;

public interface OrderService {
	
	void createOrder(Order order);
	List<Order> allOrders();
	Order getOrderById(int id);
	boolean updateOrder(int id,Order updatedOrder);
	boolean deleteOrder(int id);
	

}
