package Practice_3;

public class Car extends Vehicle {
    private int numberOfSeats;
    private String engineType;

    public Car(String vehicleNumber, String manufacturer, int year, String color,
               VehicleOwner owner, int numberOfSeats, String engineType) {
        super(vehicleNumber, manufacturer, year, color, owner);
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be greater than 0.");
        }
        if (engineType == null || engineType.trim().isEmpty()) {
            throw new IllegalArgumentException("Engine type cannot be empty.");
        }
        this.numberOfSeats = numberOfSeats;
        this.engineType = engineType.trim();
    }

    public int getNumberOfSeats() { return numberOfSeats; }
    public String getEngineType() { return engineType; }
    @Override public String getVehicleType() { return "Car"; }
    @Override protected String getSpecificDetails() {
        return "Seats: " + numberOfSeats + ", Engine type: " + engineType;
    }
}
