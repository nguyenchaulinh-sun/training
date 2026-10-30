 package Practice_2;

import java.time.LocalDate;

/**
 * Simple runnable tests for Practice 2 - Supermarket Inventory.
 *
 * Place this file in the same folder as Goods.java, Food.java,
 * Electronics.java, Crockery.java and DSHH.java.
 *
 * Run from the source root:
 *   javac Practice_2/*.java
 *   java Practice_2.TestPractice2
 */
public class TestPractice2 {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("===== PRACTICE 2 AUTOMATED TESTS =====");

        testEmptyInventory();
        testAddThreeGoodsTypes();
        testDuplicateProductCode();
        testFoodConsumption();
        testElectronicsConsumption();
        testCrockeryConsumption();
        testInvalidFoodDates();
        testInvalidElectronicsValues();

        System.out.println("\n===== TEST RESULT =====");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));

        if (failed > 0) {
            System.out.println("Some tests failed. Check the messages above.");
        } else {
            System.out.println("All tests passed!");
        }
    }

    private static void check(String testName, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + testName);
        } else {
            failed++;
            System.out.println("[FAIL] " + testName);
        }
    }

    private static boolean closeEnough(double actual, double expected) {
        return Math.abs(actual - expected) < 0.0001;
    }

    private static void testEmptyInventory() {
        DSHH inventory = new DSHH(10);

        check("Empty inventory - Food quantity is 0",
                inventory.getTotalFood() == 0);
        check("Empty inventory - Electronics quantity is 0",
                inventory.getTotalElectronics() == 0);
        check("Empty inventory - Crockery quantity is 0",
                inventory.getTotalCrockery() == 0);
        check("Empty inventory - Food VAT is 0",
                closeEnough(inventory.getTotalVATByType(Food.class), 0));
    }

    private static void testAddThreeGoodsTypes() {
        DSHH inventory = new DSHH(10);

        Food food1 = new Food(
                "F001", "Banh mi", 100, 10000,
                LocalDate.of(2026, 10, 1),
                LocalDate.of(2026, 11, 1),
                "ABC"
        );

        Electronics electronics1 = new Electronics(
                "E001", "Tivi", 5, 5000000, 24, 2.5
        );

        Crockery crockery1 = new Crockery(
                "C001", "Bat su", 60, 50000,
                "Minh Long", LocalDate.of(2026, 10, 1)
        );

        check("Add Food successfully", inventory.addGoods(food1));
        check("Add Electronics successfully", inventory.addGoods(electronics1));
        check("Add Crockery successfully", inventory.addGoods(crockery1));

        check("Food quantity total = 100",
                inventory.getTotalFood() == 100);
        check("Electronics quantity total = 5",
                inventory.getTotalElectronics() == 5);
        check("Crockery quantity total = 60",
                inventory.getTotalCrockery() == 60);
    }
    private static void testDuplicateProductCode() {
    DSHH inventory = new DSHH(10);

    Food first = new Food(
        "F001", "Banh mi", 10, 10000,
        LocalDate.of(2026, 10, 1),
        LocalDate.of(2026, 11, 1),
        "ABC"
    );

    Food duplicate = new Food(
        "f001", "Banh ngot", 5, 20000,
        LocalDate.of(2026, 10, 1),
        LocalDate.of(2026, 11, 1),
        "XYZ"
    );

    check("First product code F001 is accepted",
            inventory.addGoods(first));

    check("Duplicate code f001 is rejected",
            !inventory.addGoods(duplicate));

    check("Rejected duplicate does not change quantity",
            inventory.getTotalFood() == 10);
}

    private static void testFoodConsumption() {
        Food expiredFood = new Food(
                "F101", "Expired food", 10, 10000,
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 10, 20),
                "ABC"
        );

        Food freshFood = new Food(
                "F102", "Fresh food", 10, 10000,
                LocalDate.of(2026, 10, 1),
                LocalDate.of(2026, 11, 20),
                "ABC"
        );

        Food noStockFood = new Food(
                "F103", "No stock food", 0, 10000,
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 10, 20),
                "ABC"
        );

        check("Expired Food with stock is Hard to sell",
                expiredFood.evaluateConsumption().equals("Hard to sell"));
        check("Food not expired is Not evaluated",
                freshFood.evaluateConsumption().equals("Not evaluated"));
        check("Expired Food with zero stock is Not evaluated",
                noStockFood.evaluateConsumption().equals("Not evaluated"));
    }

    private static void testElectronicsConsumption() {
        Electronics quantity2 = new Electronics(
                "E101", "Device 2", 2, 100000, 12, 1.0
        );
        Electronics quantity3 = new Electronics(
                "E102", "Device 3", 3, 100000, 12, 1.0
        );

        check("Electronics quantity 2 is Sold",
                quantity2.evaluateConsumption().equals("Sold"));
        check("Electronics quantity 3 is Not evaluated",
                quantity3.evaluateConsumption().equals("Not evaluated"));
    }

    private static void testCrockeryConsumption() {
        Crockery slowSale = new Crockery(
                "C101", "Old crockery", 60, 50000,
                "Maker", LocalDate.of(2026, 10, 1)
        );

        Crockery quantityBoundary = new Crockery(
                "C102", "50 items", 50, 50000,
                "Maker", LocalDate.of(2026, 10, 1)
        );

        Crockery recentArrival = new Crockery(
                "C103", "Recent arrival", 60, 50000,
                "Maker", LocalDate.of(2026, 10, 25)
        );

        check("Crockery quantity > 50 and stored > 10 days is Slow sale",
                slowSale.evaluateConsumption().equals("Slow sale"));
        check("Crockery quantity exactly 50 is Not evaluated",
                quantityBoundary.evaluateConsumption().equals("Not evaluated"));
        check("Crockery stored 5 days is Not evaluated",
                recentArrival.evaluateConsumption().equals("Not evaluated"));
    }

    private static void testInvalidFoodDates() {
        boolean exceptionThrown = false;

        try {
            new Food(
                    "F201", "Invalid date food", 10, 10000,
                    LocalDate.of(2026, 10, 20),
                    LocalDate.of(2026, 10, 10),
                    "ABC"
            );
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        check("Food expiration date before manufacturing date is rejected",
                exceptionThrown);
    }

    private static void testInvalidElectronicsValues() {
        boolean negativeWarrantyRejected = false;
        boolean negativeCapacityRejected = false;

        try {
            new Electronics("E201", "Bad warranty", 1, 1000, -1, 1.0);
        } catch (IllegalArgumentException e) {
            negativeWarrantyRejected = true;
        }

        try {
            new Electronics("E202", "Bad capacity", 1, 1000, 12, -0.5);
        } catch (IllegalArgumentException e) {
            negativeCapacityRejected = true;
        }

        check("Negative warranty is rejected", negativeWarrantyRejected);
        check("Negative capacity is rejected", negativeCapacityRejected);
    }
}
