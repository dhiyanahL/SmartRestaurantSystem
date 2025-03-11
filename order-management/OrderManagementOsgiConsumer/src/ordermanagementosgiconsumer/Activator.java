package ordermanagementosgiconsumer;

import ordermanagementosgi.OrderService;
import ordermanagementosgi.DineInOrder;
import ordermanagementosgi.DeliveryOrder;
import reservationmanagementosgi.ReservationService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import java.util.List;
import java.util.Scanner;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ServiceReference<?> serviceReference;
	private ServiceReference<?> reservationServiceReference;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("🔍 Searching for OrderService...");
        serviceReference = bundleContext.getServiceReference(OrderService.class.getName());
        reservationServiceReference = bundleContext.getServiceReference(ReservationService.class.getName());
        System.out.println(reservationServiceReference);
       
        

        if (serviceReference != null && reservationServiceReference != null) {
            OrderService orderService = (OrderService) bundleContext.getService(serviceReference);
            System.out.println("✅ OrderService Found!");
            
          ReservationService reservationService =(ReservationService) bundleContext.getService(reservationServiceReference);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Order Management ===");
                System.out.println("1. Create Dine In Order");
                System.out.println("2. Create Delivery Order");
                System.out.println("3. View All Dine In Orders");
                System.out.println("4. View All Delivery Orders");
                System.out.println("5. Get Dine In Orders by ID");
                System.out.println("6. Get Delivery Orders by ID");
                System.out.println("7. Update Dine In Order");
                System.out.println("8. Update Delivery Order");
                System.out.println("9. Delete Dine In Order");
                System.out.println("10. Delete Delivery Order");
                System.out.println("11. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                    	 System.out.print("Enter item name: ");
                         String itemName = scanner.nextLine();
                         System.out.print("Enter quantity: ");
                         int quantity = scanner.nextInt();
                         scanner.nextLine();
                         System.out.println("Enter Customer Name : ");
                         String name = scanner.nextLine();
                         System.out.println("Enter Contact No : ");
                         String contact = scanner.nextLine();
                         
                         System.out.println("Enter The Number Of Pax");
                         int pax = scanner.nextInt();
                         System.out.println("🥄 Searching For Tables");
                         int table = reservationService.availableTable(pax);
                         
                         orderService.createDineInOrder(new DineInOrder(0, itemName, quantity,name,contact,table));
                         break;
                    case 2:
                    	System.out.print("Enter item name: ");
                        String deliveryItemName = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int deliveryQuantity = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Customer Name : ");
                        String deliveryName = scanner.nextLine();
                        System.out.println("Enter Contact No : ");
                        String deliveryReciver= scanner.nextLine();
                        System.out.println("Enter Delivery Address");
                        String phoneNum = scanner.nextLine();
                        orderService.createDeliveryOrder(new DeliveryOrder(0, deliveryItemName, deliveryQuantity,  deliveryName, deliveryReciver,phoneNum));
                        break;
                        
                    case 3:
                    	
                    	List<DineInOrder> dineInOrders = orderService.getDineInOrders();
                    	if(dineInOrders.isEmpty()) {
                    		
                    		System.out.println("No Available Dine in Orders");
                    	}
                    	System.out.println("\n=== Dine-In Orders ===");
                        dineInOrders.forEach(System.out::println);
                        break;
                        
                    case 4:
                    	List<DeliveryOrder> deliveryOrders = orderService.getDeliveryOrders();
                    	if(deliveryOrders.isEmpty()) {
                    		
                    		System.out.println("No Avilable Deliveries");
                    	}
                    	System.out.println("\n=== Delivery Orders ===");
                        deliveryOrders.forEach(System.out::println);
                        break;
                    case 5:
                    	
                    	System.out.println("Enter Dine In Order Id : ");
                    	int id = scanner.nextInt();
                    	DineInOrder foundDineIn = orderService.getOrderByDineInId(id);
                        System.out.println(foundDineIn != null ? foundDineIn : "❌ Dine-In Order not found.");
                        break;
                        
                    case 6:
                    	
                    	System.out.println("Enter Delivery Order Id : ");
                    	int orderId = scanner.nextInt();
                    	DeliveryOrder foundDelivery = orderService.getOrderByDeliveryId(orderId);
                        System.out.println(foundDelivery != null ? foundDelivery : "❌ Delivery Order not found.");
                        break;
                        
                    case 7:
                    	
                    	System.out.println("Enter The Dine In Order To Be Updated");
                    	int dineInOrderToUpdate = scanner.nextInt();
                    	DineInOrder updatedDineIn = new DineInOrder(dineInOrderToUpdate, "Pasta", 3, "Sachini", "0776967831", 30);
                        boolean dineInUpdated = orderService.updateDineInOrder(dineInOrderToUpdate, updatedDineIn);
                        System.out.println(dineInUpdated ? "✅ Dine-In Order Updated." : "❌ Dine-In Order not found.");
                        break;
                        
                    case 8:
                    	
                    	System.out.println("Enter The Delivery Order Id To Be Updated");
                    	int deliveryOrderToUpdate = scanner.nextInt();
                    	DeliveryOrder updatedDelivery = new DeliveryOrder(deliveryOrderToUpdate, "Sushi", 2, "Gayathma", "0776435190", "456 Main St");
                        boolean deliveryUpdated = orderService.updateDeliveryOrder(deliveryOrderToUpdate, updatedDelivery);
                        System.out.println(deliveryUpdated ? "✅ Delivery Order Updated." : "❌ Delivery Order not found.");
                        break;
                        
                    case 9:
                    	
                    	System.out.println("Enter The Dine In Id That You Want To Delete");
                    	int deletingDineInId = scanner.nextInt();
                        boolean dineInDeleted = orderService.deleteDineInOrder(deletingDineInId);
                        System.out.println(dineInDeleted ? "🗑️ Dine-In Order Deleted." : "❌ Dine-In Order not found.");
                        break;
                        
                    case 10:
                    	
                    	System.out.println("Enter The Delivery Id That You Want To Delete");
                    	int deletingDeliveryId = scanner.nextInt();
                    	boolean deliveryDeleted = orderService.deleteDeliveryOrder(deletingDeliveryId);
                        System.out.println(deliveryDeleted ? "🗑️ Delivery Order Deleted." : "❌ Delivery Order not found.");
                        break;
                    	
                    	
                    case 11:
                        System.out.println("👋 Exiting Order Management.");
                        return;
                    default:
                        System.out.println("❌ Invalid choice. Try again.");
                }
                
                scanner.close();
            }
        } else {
            System.out.println("❌ OrderService Not Found in OSGi Registry.");
        }
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
