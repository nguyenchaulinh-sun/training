package Practice_2;
import java.time.LocalDate;

public class Food extends Goods {
    private LocalDate manufacturingDate;
    private LocalDate expirationDate;
    private String supplier;

    public Food(String productCode, String name, int quantity,
                double unitPrice, LocalDate manufacturingDate,
                LocalDate expirationDate, String supplier) {

        super(productCode, name, quantity, unitPrice);

        if (expirationDate.isBefore(manufacturingDate)) {
            throw new IllegalArgumentException(
                "Expiration date must be after or equal to manufacturing date"
            );
        }

        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.supplier = supplier;
    }

    @Override
    public double calculateVAT() {
        return unitPrice * quantity * 0.05;
    }

    @Override
    public String evaluateConsumption() {
        if (quantity > 0 && LocalDate.now().isAfter(expirationDate)) {
            return "Hard to sell";
        }
        return "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Manufacturing date: " + manufacturingDate
                + ", Expiration date: " + expirationDate
                + ", Supplier: " + supplier;
    }
}