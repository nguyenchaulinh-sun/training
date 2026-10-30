package Practice_2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Crockery extends Goods {
    private String manufacturer;
    private LocalDate arrivalDate;

    public Crockery(String productCode, String name, int quantity,
                    double unitPrice, String manufacturer,
                    LocalDate arrivalDate) {

        super(productCode, name, quantity, unitPrice);

        this.manufacturer = manufacturer;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public double calculateVAT() {
        return unitPrice * quantity * 0.10;
    }

    @Override
    public String evaluateConsumption() {
        long storageDays = ChronoUnit.DAYS.between(
            arrivalDate, LocalDate.now()
        );

        if (quantity > 50 && storageDays > 10) {
            return "Slow sale";
        }

        return "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Manufacturer: " + manufacturer
                + ", Arrival date: " + arrivalDate;
    }
}