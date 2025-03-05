package ordermanagementosgiconsumer;

import ordermanagementosgi.OrderService;
import ordermanagementosgi.Order;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.List;
import java.util.Scanner;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ServiceReference<?> serviceReference;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("🔍 Searching for OrderService...");
        serviceReference = bundleContext.getServiceReference(OrderService.class.getName());

        if (serviceReference != null) {
            OrderService orderService = (OrderService) bundleContext.getService(serviceReference);
            System.out.println("✅ OrderService Found!");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Order Management ===");
                System.out.println("1. Create Order");
                System.out.println("2. View All Orders");
                System.out.println("3. Get Order by ID");
                System.out.println("4. Update Order");
                System.out.println("5. Delete Order");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter item name: ");
                        String itemName = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        orderService.createOrder(new Order(0, itemName, quantity));
                        break;
                    case 2:
                        List<Order> orders = orderService.getAllOrders();
                        if (orders.isEmpty()) {
                            System.out.println("📭 No orders found.");
                        } else {
                            orders.forEach(System.out::println);
                        }
                        break;
                    case 3:
                        System.out.print("Enter order ID: ");
                        int id = scanner.nextInt();
                        Order order = orderService.getOrderById(id);
                        System.out.println(order != null ? order : "❌ Order not found.");
                        break;
                    case 4:
                        System.out.print("Enter order ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter new item name: ");
                        String newItemName = scanner.nextLine();
                        System.out.print("Enter new quantity: ");
                        int newQuantity = scanner.nextInt();
                        boolean updated = orderService.updateOrder(updateId, new Order(updateId, newItemName, newQuantity));
                        System.out.println(updated ? "✅ Order updated." : "❌ Order not found.");
                        break;
                    case 5:
                        System.out.print("Enter order ID to delete: ");
                        int deleteId = scanner.nextInt();
                        boolean deleted = orderService.deleteOrder(deleteId);
                        System.out.println(deleted ? "🗑️ Order deleted." : "❌ Order not found.");
                        break;
                    case 6:
                        System.out.println("👋 Exiting Order Management.");
                        return;
                    default:
                        System.out.println("❌ Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("❌ OrderService Not Found in OSGi Registry.");
        }
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
