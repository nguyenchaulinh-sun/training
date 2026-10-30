package Practice_2;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DSHH inventory = new DSHH(100);

        int choice;

        do {
            System.out.println("\n===== SUPERMARKET INVENTORY =====");
            System.out.println("1. Add Food");
            System.out.println("2. Add Electronics");
            System.out.println("3. Add Crockery");
            System.out.println("4. Display all goods");
            System.out.println("5. Display inventory summary");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1:
                        addFood(sc, inventory);
                        break;

                    case 2:
                        addElectronics(sc, inventory);
                        break;

                    case 3:
                        addCrockery(sc, inventory);
                        break;

                    case 4:
                        inventory.displayGoods();
                        break;

                    case 5:
                        inventory.displayInventorySummary();
                        break;

                    case 0:
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);

        sc.close();
    }

    private static void addFood(Scanner sc, DSHH inventory) {
        System.out.println("\n--- ADD FOOD ---");

        System.out.print("Product code: ");
        String code = sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.print("Unit price: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Manufacturing date (yyyy-MM-dd): ");
        LocalDate manufacturingDate =
            LocalDate.parse(sc.nextLine());

        System.out.print("Expiration date (yyyy-MM-dd): ");
        LocalDate expirationDate =
            LocalDate.parse(sc.nextLine());

        System.out.print("Supplier: ");
        String supplier = sc.nextLine();

        if (quantity < 0 || price < 0) {
            throw new IllegalArgumentException(
                "Quantity and price must be >= 0"
            );
        }

        Food food = new Food(
            code, name, quantity, price,
            manufacturingDate, expirationDate, supplier
        );

        inventory.addGoods(food);
    }

    private static void addElectronics(Scanner sc, DSHH inventory) {
        System.out.println("\n--- ADD ELECTRONICS ---");

        System.out.print("Product code: ");
        String code = sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.print("Unit price: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Warranty months: ");
        int warranty = Integer.parseInt(sc.nextLine());

        System.out.print("Capacity KW: ");
        double capacity = Double.parseDouble(sc.nextLine());

        if (quantity < 0 || price < 0) {
            throw new IllegalArgumentException(
                "Quantity and price must be >= 0"
            );
        }

        Electronics electronics = new Electronics(
            code, name, quantity, price, warranty, capacity
        );

        inventory.addGoods(electronics);
    }

    private static void addCrockery(Scanner sc, DSHH inventory) {
        System.out.println("\n--- ADD CROCKERY ---");

        System.out.print("Product code: ");
        String code = sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.print("Unit price: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Manufacturer: ");
        String manufacturer = sc.nextLine();

        System.out.print("Arrival date (yyyy-MM-dd): ");
        LocalDate arrivalDate = LocalDate.parse(sc.nextLine());

        if (quantity < 0 || price < 0) {
            throw new IllegalArgumentException(
                "Quantity and price must be >= 0"
            );
        }

        Crockery crockery = new Crockery(
            code, name, quantity, price, manufacturer, arrivalDate
        );

        inventory.addGoods(crockery);
    }
}