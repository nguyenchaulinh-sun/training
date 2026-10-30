package Practice_2;

public class Electronics extends Goods {
    private int warrantyMonths;
    private double capacityKW;

    public Electronics(String productCode, String name, int quantity,
                       double unitPrice, int warrantyMonths,
                       double capacityKW) {

        super(productCode, name, quantity, unitPrice);

        if (warrantyMonths < 0 || capacityKW < 0) {
            throw new IllegalArgumentException(
                "Warranty and capacity must be >= 0"
            );
        }

        this.warrantyMonths = warrantyMonths;
        this.capacityKW = capacityKW;
    }

    @Override
    public double calculateVAT() {
        return unitPrice * quantity * 0.10;
    }

    @Override
    public String evaluateConsumption() {
        if (quantity < 3) {
            return "Sold";
        }
        return "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Warranty: " + warrantyMonths + " months"
                + ", Capacity: " + capacityKW + " KW";
    }
}