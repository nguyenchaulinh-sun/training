package Practice_3;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner SC = new Scanner(System.in);
    private static final VehicleManager MANAGER = new VehicleManager();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Choose: ");
            try {
                switch (choice) {
                    case 1: addVehicle(); break;
                    case 2: searchVehicle(); break;
                    case 3: searchOwnerVehicles(); break;
                    case 4: deleteByManufacturer(); break;
                    case 5: showManufacturerWithMostVehicles(); break;
                    case 6: showSortedTypes(); break;
                    case 7: showStatistics(); break;
                    case 0:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Please choose from 0 to 7.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid data: " + ex.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== POLICE VEHICLE MANAGEMENT =====");
        System.out.println("1. Add vehicle");
        System.out.println("2. Search by vehicle number");
        System.out.println("3. Find vehicles by owner ID");
        System.out.println("4. Delete vehicles by manufacturer");
        System.out.println("5. Manufacturer(s) with most vehicles");
        System.out.println("6. Sort vehicle types by quantity (descending)");
        System.out.println("7. Vehicle statistics");
        System.out.println("0. Exit");
    }

    private static void addVehicle() {
        System.out.println("1. Car  2. Motorbike  3. Truck");
        int type = readInt("Vehicle type: ");
        String number = readLine("Vehicle number (5 characters): ");
        String manufacturer = readLine("Manufacturer (Honda/Yamaha/Toyota/Suzuki): ");
        int year = readInt("Year of manufacture: ");
        String color = readLine("Color: ");

        String id = readLine("Owner ID (12 digits): ");
        String name = readLine("Owner full name: ");
        String email = readLine("Owner email: ");
        VehicleOwner owner = new VehicleOwner(id, name, email);

        Vehicle vehicle;
        if (type == 1) {
            int seats = readInt("Number of seats: ");
            String engine = readLine("Engine type: ");
            vehicle = new Car(number, manufacturer, year, color, owner, seats, engine);
        } else if (type == 2) {
            double capacity = readDouble("Capacity (cc): ");
            vehicle = new Motorbike(number, manufacturer, year, color, owner, capacity);
        } else if (type == 3) {
            double tonnage = readDouble("Tonnage (tons): ");
            vehicle = new Truck(number, manufacturer, year, color, owner, tonnage);
        } else {
            System.out.println("Invalid vehicle type.");
            return;
        }

        if (MANAGER.addVehicle(vehicle)) System.out.println("Vehicle added successfully.");
        else System.out.println("Vehicle number already exists.");
    }

    private static void searchVehicle() {
        Vehicle vehicle = MANAGER.findByVehicleNumber(readLine("Vehicle number: "));
        System.out.println(vehicle == null ? "Vehicle not found." : vehicle);
    }

    private static void searchOwnerVehicles() {
        List<Vehicle> result = MANAGER.findByOwnerId(readLine("Owner ID: "));
        if (result.isEmpty()) System.out.println("No vehicles found for this owner.");
        else for (Vehicle vehicle : result) System.out.println(vehicle);
    }

    private static void deleteByManufacturer() {
        String manufacturer = readLine("Manufacturer: ");
        int deleted = MANAGER.deleteByManufacturer(manufacturer);
        System.out.println("Deleted vehicles: " + deleted);
    }

    private static void showManufacturerWithMostVehicles() {
        List<String> result = MANAGER.manufacturersWithMostVehicles();
        if (result.isEmpty()) System.out.println("No vehicles are being managed.");
        else System.out.println("Manufacturer(s) with most vehicles: " + result);
    }

    private static void showSortedTypes() {
        for (Map.Entry<String, Integer> entry : MANAGER.sortTypesByCountDescending()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void showStatistics() {
        System.out.println("===== VEHICLE STATISTICS =====");
        for (Map.Entry<String, Integer> entry : MANAGER.countByVehicleType().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Total vehicles: " + MANAGER.size());
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SC.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(readLine(prompt)); }
            catch (NumberFormatException ex) { System.out.println("Please enter a whole number."); }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try { return Double.parseDouble(readLine(prompt)); }
            catch (NumberFormatException ex) { System.out.println("Please enter a valid number."); }
        }
    }
}
