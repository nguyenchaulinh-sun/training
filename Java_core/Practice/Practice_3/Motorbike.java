package Practice_3;

public class Motorbike extends Vehicle {
    private double capacity;

    public Motorbike(String vehicleNumber, String manufacturer, int year, String color,
                     VehicleOwner owner, double capacity) {
        super(vehicleNumber, manufacturer, year, color, owner);
        if (capacity <= 0) {
            throw new IllegalArgumentException("Motorbike capacity must be greater than 0.");
        }
        this.capacity = capacity;
    }

    public double getCapacity() { return capacity; }
    @Override public String getVehicleType() { return "Motorbike"; }
    @Override protected String getSpecificDetails() { return "Capacity: " + capacity + " cc"; }
}
