package Practice_3;

public class Truck extends Vehicle {
    private double tonnage;

    public Truck(String vehicleNumber, String manufacturer, int year, String color,
                 VehicleOwner owner, double tonnage) {
        super(vehicleNumber, manufacturer, year, color, owner);
        if (tonnage <= 0) {
            throw new IllegalArgumentException("Truck tonnage must be greater than 0.");
        }
        this.tonnage = tonnage;
    }

    public double getTonnage() { return tonnage; }
    @Override public String getVehicleType() { return "Truck"; }
    @Override protected String getSpecificDetails() { return "Tonnage: " + tonnage + " tons"; }
}
