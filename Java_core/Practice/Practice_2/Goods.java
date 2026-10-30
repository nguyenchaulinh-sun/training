package Practice_2;

public abstract class Goods {
    protected String productCode;
    protected String name;
    protected int quantity;
    protected double unitPrice;

    public Goods(String productCode, String name,
                 int quantity, double unitPrice) {
        this.productCode = productCode;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract double calculateVAT();

    public abstract String evaluateConsumption();

    public double getInventoryValue() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return "Code: " + productCode
                + ", Name: " + name
                + ", Quantity: " + quantity
                + ", Unit price: " + unitPrice;
    }
}
