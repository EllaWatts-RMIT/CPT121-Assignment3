// Subclass for Assignment 3, Programming 1
public class BulkCargo extends Cargo {

	private double cubicMeters;
	private double transportKM;
	private static double rate = 0.30;

	public BulkCargo(String customerName, String pickupAddress,
	        String deliveryAddress, int ID, double cubicMeters,
	        double transportKM) {
		super(customerName, pickupAddress, deliveryAddress, ID);
		this.cubicMeters = cubicMeters;
		this.transportKM = transportKM;
		this.cost = calculateCost();

	}
	
	// Overloaded constructor as discussed in cargo class. 
	public BulkCargo(String customerName, String pickupAddress,
	        String deliveryAddress, int ID, int nextID, double cubicMeters,
	        double transportKM) {
		super(customerName, pickupAddress, deliveryAddress, ID, nextID);
		this.cubicMeters = cubicMeters;
		this.transportKM = transportKM;
		this.cost = calculateCost();

	}

	public double calculateCost() {
		return cubicMeters * transportKM * rate;
	}

	public void displayCargo() {
		System.out.printf(
		        " %-15s %d\n %-15s %s\n %-15s %s\n %-15s %s\n "
		        + "%-15s %.3f\n %-15s %.3f\n %-15s $%.2f",
		        "ID: ", ID, "Customer: ", customerName, "Pickup: ",
		        pickupAddress, "Delivery: ", deliveryAddress,
		        "Cubic meters: ", cubicMeters, "Distance: ",
		        transportKM, "Cost: ", cost);
	}
	
	public double getVolume() {
		return cubicMeters;
	}
	
	public double getDistance() {
		return transportKM;
	}
}