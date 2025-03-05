package com.order.service;

import java.util.ArrayList;
import java.util.List;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class OrderServiceImpl implements OrderService,BundleActivator {
	
	
	private ServiceRegistration<?> serviceRegistration;
	private final List<Order> orders = new ArrayList();
	private int nextOrderId = 1;

	@Override
	public void createOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> allOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrderById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateOrder(int id, Order updatedOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		
		serviceRegistration = context.registerService(OrderService.class.getName(), this, null);
		
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
