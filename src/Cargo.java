// data class for cargo in Assignment 3, 
// Programming 1
public abstract class Cargo {

	protected String customerName;
	protected String pickupAddress;
	protected String deliveryAddress;
	protected int ID;
	protected static int nextID;
	protected double cost;

	public Cargo(String customerName, String pickupAddress,
	        String deliveryAddress, int ID) {
		this.customerName = customerName;
		this.pickupAddress = pickupAddress;
		this.deliveryAddress = deliveryAddress;
		this.ID = ID;
		this.nextID = ID + 1;
	}

	// Overloaded constructor with nextID due to differences 
	// in creating a cargo object from user input versus from 
	// save data file. Only save data has nextID as an input
	// variable. 
	public Cargo(String customerName, String pickupAddress,
	        String deliveryAddress, int ID, int nextID) {
		this.customerName = customerName;
		this.pickupAddress = pickupAddress;
		this.deliveryAddress = deliveryAddress;
		this.ID = ID;
		this.nextID = nextID;
	}

	public abstract double calculateCost();

	public abstract void displayCargo();

	// Getters are required for nearly all fields due to 
	// the requirement of writing all data to save files. 
	public int getID() {
		return ID;
	}

	public int getNextID() {
		return nextID;
	}

	public String getCustomer() {
		return customerName;
	}

	public String getPickup() {
		return pickupAddress;
	}

	public String getDelivery() {
		return deliveryAddress;
	}

	// Used for brief display of all cargo
	public void displayAll() {
		String cargoType = " ";
		if (this instanceof BulkCargo) {
			cargoType = "Bulk";
		} else if (this instanceof PackagedCargo) {
			cargoType = "Packaged";
		} else if (this instanceof RefrigeratedPackagedCargo) {
			cargoType = "Refrigerated";
		}
		System.out.printf("\n%-5s%-25s%-25s%-25s%-13s$%.2f", ID,
		        customerName, pickupAddress, deliveryAddress,
		        cargoType, cost);
	}
}