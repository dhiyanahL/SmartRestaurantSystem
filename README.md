# SmartRestaurantSystem

The Smart Restaurant Management System is a modular application designed using the
OSGi framework to enhance restaurant operations through efficient service communication.
The system follows a Producer-Consumer architecture, ensuring flexibility and scalability
while enabling seamless interaction between different restaurant subsystems.
This system is divided into four key functional areas:

1. Order Management – Handles customer orders.
2. Ingredient Management – Tracks ingredient usage and requests restocks when stock
is low.
3. Reservation System – Manages table reservations and updates table availability.
4. Delivery Management – Receives deliver orders from Order Service, processes
them, updates the delivery status and notifies the system in real-time

Each subsystem consists of an OSGi Producer Bundle that provides services and an OSGi
Consumer Bundle that utilizes those services.
By leveraging OSGi’s modular architecture, the system ensures that services remain
independent yet interconnected.
