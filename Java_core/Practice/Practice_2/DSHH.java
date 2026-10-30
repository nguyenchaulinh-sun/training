package Practice_2;

public class DSHH {
    private Goods[] goodsList;
    private int count;

    public DSHH(int capacity) {
        goodsList = new Goods[capacity];
        count = 0;
    }

    // Check duplicate product code
    public boolean isDuplicateCode(String code) {
        for (int i = 0; i < count; i++) {
            if (goodsList[i].getProductCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    // Add goods to array
    public boolean addGoods(Goods goods) {
        if (count >= goodsList.length) {
            System.out.println("Inventory is full!");
            return false;
        }

        if (isDuplicateCode(goods.getProductCode())) {
            System.out.println("Product code already exists!");
            return false;
        }

        goodsList[count] = goods;
        count++;

        System.out.println("Add goods successfully!");
        return true;
    }

    // Display all goods
    public void displayGoods() {
        if (count == 0) {
            System.out.println("Inventory is empty!");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(goodsList[i]);
            System.out.println("VAT: " + goodsList[i].calculateVAT());
            System.out.println(
                "Consumption: " + goodsList[i].evaluateConsumption()
            );
            System.out.println("--------------------------");
        }
    }

    // Calculate total inventory quantity by type
    public int getTotalFood() {
        int total = 0;

        for (int i = 0; i < count; i++) {
            if (goodsList[i] instanceof Food) {
                total += goodsList[i].getQuantity();
            }
        }

        return total;
    }

    public int getTotalElectronics() {
        int total = 0;

        for (int i = 0; i < count; i++) {
            if (goodsList[i] instanceof Electronics) {
                total += goodsList[i].getQuantity();
            }
        }

        return total;
    }

    public int getTotalCrockery() {
        int total = 0;

        for (int i = 0; i < count; i++) {
            if (goodsList[i] instanceof Crockery) {
                total += goodsList[i].getQuantity();
            }
        }

        return total;
    }

    // Calculate total VAT by type
    public double getTotalVATByType(Class<?> type) {
        double total = 0;

        for (int i = 0; i < count; i++) {
            if (type.isInstance(goodsList[i])) {
                total += goodsList[i].calculateVAT();
            }
        }

        return total;
    }

    public void displayInventorySummary() {
        System.out.println("\n===== INVENTORY SUMMARY =====");

        System.out.println("Food quantity: " + getTotalFood());
        System.out.println("Electronics quantity: " + getTotalElectronics());
        System.out.println("Crockery quantity: " + getTotalCrockery());

        System.out.println(
            "Food VAT: " + getTotalVATByType(Food.class)
        );

        System.out.println(
            "Electronics VAT: " + getTotalVATByType(Electronics.class)
        );

        System.out.println(
            "Crockery VAT: " + getTotalVATByType(Crockery.class)
        );
    }
}