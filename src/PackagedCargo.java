// Subclass for Assignment 3, Programming 1
public class PackagedCargo extends Cargo {

	private double heightCM;
	private double widthCM;
	private double depthCM;
	private double weightKG;
	private double cubicWeightKG;
	private static double rate = 0.15;

	public PackagedCargo(String customerName, String pickupAddress,
	        String deliveryAddress, int ID, double heightCM,
	        double widthCM, double depthCM, double weightKG) {
		super(customerName, pickupAddress, deliveryAddress, ID);
		this.heightCM = heightCM;
		this.widthCM = widthCM;
		this.depthCM = depthCM;
		this.weightKG = weightKG;
		this.cubicWeightKG = heightCM * widthCM * depthCM * 250
		        / 1000000;
		this.cost = calculateCost();

	}

	// Overloaded constructor as discussed in cargo class. 
	public PackagedCargo(String customerName, String pickupAddress,
	        String deliveryAddress, int ID, int nextID,
	        double heightCM, double widthCM, double depthCM,
	        double weightKG) {
		super(customerName, pickupAddress, deliveryAddress, ID,
		        nextID);
		this.heightCM = heightCM;
		this.widthCM = widthCM;
		this.depthCM = depthCM;
		this.weightKG = weightKG;
		this.cubicWeightKG = heightCM * widthCM * depthCM * 250
		        / 1000000;
		this.cost = calculateCost();

	}

	public double calculateCost() {
		double tempCost;
		if (weightKG > cubicWeightKG) {
			tempCost = weightKG * rate;
		} else {
			tempCost = cubicWeightKG * rate;
		}
		return tempCost;
	}

	public void displayCargo() {
		System.out.printf(
		        " %-19s %d\n %-19s %s\n %-19s %s\n %-19s %s\n "
		                + "%-19s %.2f\n %-19s %.2f\n %-19s %.2f\n "
		                + "%-19s %.3f\n %-19s %.3f\n %-19s $%.2f",
		        "ID: ", ID, "Customer: ", customerName, "Pickup: ",
		        pickupAddress, "Delivery: ", deliveryAddress,
		        "Height (cm): ", heightCM, "Width  (cm): ", widthCM,
		        "Depth  (cm): ", depthCM, "Weight (kg): ", weightKG,
		        "Cubic weight (kg): ", cubicWeightKG, "Cost: ", cost);
	}

	// All getter methods needed for writing out object data to 
	// save file. 
	public double getHeight() {
		return heightCM;
	}

	public double getWidth() {
		return widthCM;
	}

	public double getDepth() {
		return depthCM;
	}

	public double getWeight() {
		return weightKG;
	}
}