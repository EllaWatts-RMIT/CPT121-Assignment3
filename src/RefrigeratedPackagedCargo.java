// Subclass for Assignment 3, Programming 1
public class RefrigeratedPackagedCargo extends Cargo {

	private double heightCM;
	private double widthCM;
	private double depthCM;
	private double weightKG;
	private double cubicWeightKG;
	private static double rate = 0.25;
	private boolean frozen;

	public RefrigeratedPackagedCargo(String customerName,
	        String pickupAddress, String deliveryAddress, int ID,
	        double heightCM, double widthCM, double depthCM,
	        double weightKG, boolean frozen) {
		super(customerName, pickupAddress, deliveryAddress, ID);
		this.heightCM = heightCM;
		this.widthCM = widthCM;
		this.depthCM = depthCM;
		this.weightKG = weightKG;
		this.cubicWeightKG = heightCM * widthCM * depthCM * 250
		        / 1000000;
		this.frozen = frozen;
		this.cost = calculateCost();
	}

	// Overloaded constructor as discussed in argo class. 
	public RefrigeratedPackagedCargo(String customerName,
	        String pickupAddress, String deliveryAddress, int ID,
	        int nextID, double heightCM, double widthCM,
	        double depthCM, double weightKG, boolean frozen) {
		super(customerName, pickupAddress, deliveryAddress, ID,
		        nextID);
		this.heightCM = heightCM;
		this.widthCM = widthCM;
		this.depthCM = depthCM;
		this.weightKG = weightKG;
		this.cubicWeightKG = heightCM * widthCM * depthCM * 250
		        / 1000000;
		this.frozen = frozen;
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
		String state = "chilled";
		if (frozen) {
			state = "frozen";
		}
		System.out.printf(
		        " %-19s %d\n %-19s %s\n %-19s %s\n %-19s %s\n "
		                + "%-19s %.2f\n %-19s %.2f\n %-19s %.2f\n "
		                + "%-19s %.3f\n %-19s %.3f\n %-19s %s\n "
		                + "%-19s $%.2f", "ID: ", ID, "Customer: ",
		        customerName, "Pickup: ", pickupAddress, "Delivery: ",
		        deliveryAddress, "Height (cm): ", heightCM,
		        "Width  (cm): ", widthCM, "Depth  (cm): ", depthCM,
		        "Weight (kg): ", weightKG, "Cubic weight (kg): ",
		        cubicWeightKG, "Condition: ", state, "Cost: ", cost);
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

	public boolean getFrozen() {
		return frozen;
	}
}