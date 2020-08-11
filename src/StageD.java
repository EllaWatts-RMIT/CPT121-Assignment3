// Assignment 3 for Programming 1
// Program designed to manage a catalogue of cargo items

import java.util.*;
import java.io.*;

public class StageD {

	public Scanner console = new Scanner(System.in);
	public static int MAX_CARGO = 4;
	public int currentNumCargo = 0;
	public Cargo cargo[] = new Cargo[MAX_CARGO];

	// Constructor finds any pre-existing cargo files (stored in
	// working directory) and loads menu.
	public StageD() {
		System.out.print(
		        "-------------Cargo Records Manager--------------");
		int maxID = 99;
		try {
			Scanner scanMax = new Scanner(new File("maxID.txt"));
			maxID = scanMax.nextInt();
			scanMax.close();
		} catch (FileNotFoundException e) {
			System.out.println("\nNo save data found.");
		}

		for (int i = 0; (i < (maxID - 99)
		        && currentNumCargo < MAX_CARGO); i++) {
			try {
				loadFromFiles(i);
			} catch (IllegalArgumentException e) {
				System.out.print(
				        "\nSave data was corrupted and could not be loaded.");
			} catch (FileNotFoundException e) {
				System.out.print("FileNotFoundException");
			} catch (IOException e) {
				System.out.print("\n"
				        + "Save data was corrupted and could not be loaded.");
			}
		}
		displayMenu();
	}

	public void loadFromFiles(int i) throws FileNotFoundException,
	        IOException, IllegalArgumentException {
		int ID = 100 + i;
		String filepath = ID + ".txt";
		File cargoFile = new File(filepath);
		if (cargoFile.exists()) {
			Scanner scanner = new Scanner(cargoFile);
			String customerName;
			if (scanner.hasNextLine()) {
				customerName = scanner.nextLine();
			} else {
				scanner.close();
				throw new IllegalArgumentException();
			}
			if (customerName.equalsIgnoreCase("REMOVED")) {
				scanner.close();
				return;
			}
			String pickupAddress;
			if (scanner.hasNextLine()) {
				pickupAddress = scanner.nextLine();
			} else {
				scanner.close();
				throw new IllegalArgumentException();
			}
			String deliveryAddress;
			if (scanner.hasNextLine()) {
				deliveryAddress = scanner.nextLine();
			} else {
				scanner.close();
				throw new IllegalArgumentException();
			}
			int nextID;
			if (scanner.hasNextInt()) {
				nextID = scanner.nextInt();
			} else {
				scanner.close();
				throw new IllegalArgumentException();
			}
			scanner.nextLine();
			String cargoType;
			if (scanner.hasNextLine()) {
				cargoType = scanner.nextLine().trim();
			} else {
				scanner.close();
				throw new IllegalArgumentException();
			}
			if (cargoType.equals("B")) {
				double cubicMeters = scanner.nextDouble();
				if (scanner.hasNextDouble()) {
					cubicMeters = scanner.nextDouble();
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}
				scanner.nextLine();
				double transportKM;
				if (scanner.hasNextDouble()) {
					transportKM = scanner.nextDouble();
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}
				cargo[currentNumCargo++] = new BulkCargo(customerName,
				        pickupAddress, deliveryAddress, ID, nextID,
				        cubicMeters, transportKM);

			} else if (cargoType.equals("P")) {
				double heightCM;
				if (scanner.hasNextDouble()) {
					heightCM = scanner.nextDouble();
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}
				scanner.nextLine();
				double widthCM;
				if (scanner.hasNextDouble()) {
					widthCM = scanner.nextDouble();
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}
				scanner.nextLine();
				double depthCM;
				if (scanner.hasNextDouble()) {
					depthCM = scanner.nextDouble();
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}
				scanner.nextLine();
				double weightKG;
				if (scanner.hasNextDouble()) {
					weightKG = scanner.nextDouble();
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}
				cargo[currentNumCargo++] = new PackagedCargo(
				        customerName, pickupAddress, deliveryAddress,
				        ID, nextID, heightCM, widthCM, depthCM,
				        weightKG);

			} else if (cargoType.equals("R")) {
				double heightCM;
				if (scanner.hasNextDouble()) {
					heightCM = scanner.nextDouble();
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}
				scanner.nextLine();
				double widthCM;
				if (scanner.hasNextDouble()) {
					widthCM = scanner.nextDouble();
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}
				scanner.nextLine();
				double depthCM;
				if (scanner.hasNextDouble()) {
					depthCM = scanner.nextDouble();
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}
				scanner.nextLine();
				double weightKG;
				if (scanner.hasNextDouble()) {
					weightKG = scanner.nextDouble();
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}
				boolean frozen;
				if (scanner.hasNextLine()) {
					frozen = Boolean.parseBoolean(scanner.nextLine());
				} else {
					scanner.close();
					throw new IllegalArgumentException();
				}

				cargo[currentNumCargo++] = new RefrigeratedPackagedCargo(
				        customerName, pickupAddress, deliveryAddress,
				        ID, nextID, heightCM, widthCM, depthCM,
				        weightKG, frozen);
			} else {
				scanner.close();
				throw new IllegalArgumentException();
			}
			scanner.close();
		}
	}

	// Called only when the program is quit. If the program
	// terminates without properly exiting, data will not be
	// saved.
	public void writeToFiles() throws IOException {
		for (int i = 0; i < currentNumCargo; i++) {
			String filepath = cargo[i].getID() + ".txt";
			PrintWriter pw = new PrintWriter(new FileWriter(
			        filepath));
			pw.print(cargo[i].getCustomer() + "\n" + cargo[i]
			        .getPickup() + "\n" + cargo[i].getDelivery()
			        + "\n" + cargo[i].getNextID());
			pw.print(printCargo(cargo[i]));
			pw.close();
		}
		PrintWriter writeMax = new PrintWriter(new FileWriter(
		        "maxID.txt"));
		writeMax.println(cargo[currentNumCargo - 1].getID());
		writeMax.close();
	}

	// Helper method for writing out save data.
	public String printCargo(Cargo object) {
		String printable = " ";
		if (object instanceof BulkCargo) {
			printable = "\nB\n" + ((BulkCargo) object).getVolume()
			        + "\n" + ((BulkCargo) object).getDistance();
		} else if (object instanceof PackagedCargo) {
			printable = "\nP\n" + ((PackagedCargo) object).getHeight()
			        + "\n" + ((PackagedCargo) object).getWidth()
			        + "\n" + ((PackagedCargo) object).getDepth()
			        + "\n" + ((PackagedCargo) object).getWeight();
		} else if (object instanceof RefrigeratedPackagedCargo) {
			printable = "\nR\n" + ((RefrigeratedPackagedCargo) object)
			        .getHeight() + "\n"
			        + ((RefrigeratedPackagedCargo) object).getWidth()
			        + "\n" + ((RefrigeratedPackagedCargo) object)
			                .getDepth() + "\n"
			        + ((RefrigeratedPackagedCargo) object).getWeight()
			        + "\n" + String.valueOf(
			                ((RefrigeratedPackagedCargo) object)
			                        .getFrozen());
		}
		return printable;
	}

	public void displayMenu() {
		System.out.print(
		        "\n\n----------------------Menu----------------------"
		                + "\n\n(1) Add cargo" + "\n(2) Show all cargo"
		                + "\n(3) Find cargo" + "\n(4) Remove cargo"
		                + "\n(5) Quit program"
		                + "\n\nPlease select a menu item (enter "
		                + "corresponding number): ");

		String menuSelect = console.nextLine().trim();

		if (menuSelect.equals("1")) {
			try {
				addNewCargo();

			} catch (CargoException e) {
				System.out.println(e.getMessage());

			} catch (IllegalArgumentException e) {
				System.out.print("\nOops. That's not a valid input.");

			} finally {
				returnToMenu();
			}

		} else if (menuSelect.equals("2")) {
			displayAllCargo();
			returnToMenu();

		} else if (menuSelect.equals("3")) {
			findCargo();
			returnToMenu();

		} else if (menuSelect.equals("4")) {
			removeCargo();
			returnToMenu();

		} else if (menuSelect.equals("5")) {
			System.out.print("\n\nQuitting program. Goodbye");
			try {
				writeToFiles();
			} catch (IOException e) {
				System.out.print(
				        "\n\nAn error occured. Data not saved.");
			} finally {
				System.exit(0);
			}

		} else {
			System.out.print(
			        "\n\nThat's not a valid input. Please try again.");
			displayMenu();
		}
	}

	// Throws CargoException if any inputs are out of specified
	// bounds, throws IllegalArgumentException if any inputs are
	// not of the expected type.
	public void addNewCargo() throws CargoException,
	        IllegalArgumentException {
		if (currentNumCargo >= MAX_CARGO) {
			throw new CargoException(
			        "Number of cargo items has reached maximum.");
		}

		System.out.print(
		        "\n\n--------------------New Cargo-------------------"
		                + "\n\nPlease enter the customer's name: ");
		String customerName = console.nextLine();
		System.out.print("\nPlease enter the pickup address: ");
		String pickupAddress = console.nextLine();
		System.out.print("\nPlease enter the delivery address: ");
		String deliveryAddress = console.nextLine();
		System.out.print(
		        "\nWhat type of cargo is being transported? Bulk (B),"
		                + " Packaged (P), or Refrigerated (R)? ");
		String cargoType = console.nextLine().trim();

		int ID;
		if (currentNumCargo == 0) {
			ID = 100;
		} else {
			ID = cargo[currentNumCargo - 1].getNextID();
		}

		if (cargoType.equalsIgnoreCase("B")) {
			addBulk(customerName, pickupAddress, deliveryAddress, ID);
		} else if (cargoType.equalsIgnoreCase("P")) {
			addPackaged(customerName, pickupAddress, deliveryAddress,
			        ID);
		} else if (cargoType.equalsIgnoreCase("R")) {
			addRefrigerated(customerName, pickupAddress,
			        deliveryAddress, ID);
		} else {
			throw new IllegalArgumentException();
		}

		System.out.print("\nCargo added successfully.\n\n");
		cargo[currentNumCargo++].displayCargo();
	}

	// addBulk/Packaged/Refrigerated are helper functions, mostly
	// designed to avoid arrow code.
	public void addBulk(String customerName, String pickupAddress,
	        String deliveryAddress, int ID) throws CargoException {
		System.out.print(
		        "\nPlease enter the volume of the cargo in cubic meters: ");
		double cubicMeters;
		if (console.hasNextDouble()) {
			cubicMeters = console.nextDouble();
			console.nextLine();
		} else {
			throw new IllegalArgumentException();
		}
		if (cubicMeters > 6) {
			throw new CargoException(
			        "\nBulk cargo can't exceed 6 cubic meters. Cargo not added.");
		}

		System.out.print(
		        "\nPlease specify how far the cargo will be transported "
		                + "in km:  ");
		double transportKM = 2;
		if (console.hasNextDouble()) {
			transportKM = console.nextDouble();
			console.nextLine();
		} else {
			throw new IllegalArgumentException();
		}

		cargo[currentNumCargo] = new BulkCargo(customerName,
		        pickupAddress, deliveryAddress, ID, cubicMeters,
		        transportKM);
	}

	public void addPackaged(String customerName, String pickupAddress,
	        String deliveryAddress, int ID) throws CargoException {
		System.out.print(
		        "\nPlease enter the height, width and depth of the "
		                + "item in cm (no commas): ");
		double heightCM, widthCM, depthCM, weightKG;
		if (console.hasNextDouble()) {
			heightCM = console.nextDouble();
		} else {
			throw new IllegalArgumentException();
		}
		if (console.hasNextDouble()) {
			widthCM = console.nextDouble();
		} else {
			throw new IllegalArgumentException();
		}
		if (console.hasNextDouble()) {
			depthCM = console.nextDouble();
		} else {
			throw new IllegalArgumentException();
		}
		if (heightCM > 180 || widthCM > 180 || depthCM > 180) {
			throw new CargoException(
			        "\nDimensions cannot exceed 180cm. Cargo not added.");
		}
		console.nextLine();
		System.out.print(
		        "\nPlease enter the weight of the cargo in kg: ");
		if (console.hasNextDouble()) {
			weightKG = console.nextDouble();
		} else {
			throw new IllegalArgumentException();
		}
		if (weightKG > 60) {
			throw new CargoException("Weigth cannot exceed 60kg.");
		}

		cargo[currentNumCargo] = new PackagedCargo(customerName,
		        pickupAddress, deliveryAddress, ID, heightCM, widthCM,
		        depthCM, weightKG);
	}

	public void addRefrigerated(String customerName,
	        String pickupAddress, String deliveryAddress, int ID)
	        throws CargoException {
		System.out.print(
		        "\n Please enter the height, width and depth of the "
		                + "item in cm (no commas): ");
		double heightCM, widthCM, depthCM, weightKG;
		if (console.hasNextDouble()) {
			heightCM = console.nextDouble();
		} else {
			throw new IllegalArgumentException();
		}
		if (console.hasNextDouble()) {
			widthCM = console.nextDouble();
		} else {
			throw new IllegalArgumentException();
		}
		if (console.hasNextDouble()) {
			depthCM = console.nextDouble();
		} else {
			throw new IllegalArgumentException();
		}
		if (heightCM > 180 || widthCM > 180 || depthCM > 180) {
			throw new CargoException(
			        "\nDimensions cannot exceed 180cm. Cargo not added.");
		}
		console.nextLine();
		System.out.print(
		        "\nPlease enter the weight of the cargo in kg: ");
		if (console.hasNextDouble()) {
			weightKG = console.nextDouble();
		} else {
			throw new IllegalArgumentException();
		}
		if (weightKG > 60) {
			throw new CargoException("Weigth cannot exceed 60kg.");
		}
		System.out.print("\nIs the item frozen (Y/N): ");
		String frozenInput = console.nextLine().trim();
		boolean frozen = false;
		if (frozenInput.equalsIgnoreCase("Y")) {
			frozen = true;
		} else if (!frozenInput.equalsIgnoreCase("N")) {
			throw new IllegalArgumentException();
		}

		cargo[currentNumCargo] = new RefrigeratedPackagedCargo(
		        customerName, pickupAddress, deliveryAddress, ID,
		        heightCM, widthCM, depthCM, weightKG, frozen);
	}

	public void returnToMenu() {
		System.out.print("\n\nPress Enter↵ to return to Menu.");
		console.nextLine();
		displayMenu();
	}

	// Calls displayAll() method of cargo item when user wishes to
	// view brief details of all items.
	public void displayAllCargo() {
		if (currentNumCargo == 0) {
			System.out.print(
			        "\n--------------------All Cargo------------------"
			                + "-\nNo cargo found.");
			return;
		}
		System.out.printf(
		        "\n--------------------All Cargo------------------"
		                + "-\n\n%-5s%-25s%-25s%-25s%-13s%-6s\n----"
		                + "---------------------------------------"
		                + "---------------------------------------"
		                + "----------------------", "ID", "Customer",
		        "Pickup", "Delivery", "Type", "Cost");
		for (int i = 0; i < currentNumCargo; i++) {
			cargo[i].displayAll();
		}
	}

	public void findCargo() {
		System.out.print("\n\n-------------------Find Cargo-----"
		        + "--------------\n\nEnter the ID number: ");
		int searchID = console.nextInt();
		console.nextLine();
		if (currentNumCargo == 0) {
			System.out.print("Cargo not found.");
		}
		for (int i = 0; i < currentNumCargo; i++) {
			if (searchID == cargo[i].getID()) {
				cargo[i].displayCargo();
				return;
			}
			if (i == currentNumCargo - 1) {
				System.out.print("Cargo not found.");
			}
		}
	}

	// Uses helper method replaceCargo() to find an item by ID and
	// remove it from the cargo array, as well as changing it's save
	// file so it won't be loaded again.
	public void removeCargo() {
		System.out.print("\n\n-------------------Find Cargo-----"
		        + "--------------\n\nEnter the ID number of the "
		        + "cargo to remove: ");
		int searchID = console.nextInt();
		console.hasNextLine();
		if (currentNumCargo == 0) {
			System.out.print("Cargo not found.");
		}
		for (int i = 0; i < currentNumCargo; i++) {
			if (searchID == cargo[i].getID()) {
				try {
					replaceCargo(i);
					System.out.print("Cargo successfully removed.");
					return;
				} catch (IOException e) {
					System.out.print(
					        "Removal failed. Please try again");
				}
			}
			if (i == currentNumCargo - 1) {
				System.out.print("Cargo not found.");
			}
		}
	}

	// Helper method for removeCargo()
	public void replaceCargo(int toRemove) throws IOException {
		String filepath = cargo[toRemove].getID() + ".txt";

		Cargo tempCargo[] = new Cargo[MAX_CARGO];
		for (int i = 0; i < toRemove; i++) {
			tempCargo[i] = cargo[i];
		}
		for (int i = toRemove; i < (currentNumCargo - 1); i++) {
			tempCargo[i] = cargo[i + 1];
		}
		cargo = tempCargo;

		PrintWriter pw = new PrintWriter(new FileWriter(filepath));
		pw.println("REMOVED");
		pw.close();

		currentNumCargo--;

	}

	public static void main(String args[]) {
		StageD obj = new StageD();
	}
}