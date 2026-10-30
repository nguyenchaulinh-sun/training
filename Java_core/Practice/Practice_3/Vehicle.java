package Practice_3;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

public abstract class Vehicle {
    private static final List<String> ALLOWED_MANUFACTURERS =
            Arrays.asList("Honda", "Yamaha", "Toyota", "Suzuki");

    protected String vehicleNumber;
    protected String manufacturer;
    protected int year;
    protected String color;
    protected VehicleOwner owner;

    public Vehicle(String vehicleNumber, String manufacturer, int year,
                   String color, VehicleOwner owner) {
        if (vehicleNumber == null || !vehicleNumber.matches("[A-Za-z0-9]{5}")) {
            throw new IllegalArgumentException("Vehicle number must be exactly 5 alphanumeric characters.");
        }
        if (manufacturer == null || !isAllowedManufacturer(manufacturer)) {
            throw new IllegalArgumentException("Manufacturer must be Honda, Yamaha, Toyota, or Suzuki.");
        }
        int currentYear = Year.now().getValue();
        if (year <= 2000 || year > currentYear) {
            throw new IllegalArgumentException("Year must be greater than 2000 and no later than " + currentYear + ".");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle color cannot be empty.");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Vehicle owner is required.");
        }

        this.vehicleNumber = vehicleNumber;
        this.manufacturer = canonicalManufacturer(manufacturer);
        this.year = year;
        this.color = color.trim();
        this.owner = owner;
    }

    public static boolean isAllowedManufacturer(String value) {
        if (value == null) return false;
        for (String item : ALLOWED_MANUFACTURERS) {
            if (item.equalsIgnoreCase(value.trim())) return true;
        }
        return false;
    }

    private static String canonicalManufacturer(String value) {
        for (String item : ALLOWED_MANUFACTURERS) {
            if (item.equalsIgnoreCase(value.trim())) return item;
        }
        return value.trim();
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public String getColor() { return color; }
    public VehicleOwner getOwner() { return owner; }

    public abstract String getVehicleType();
    protected abstract String getSpecificDetails();

    @Override
    public String toString() {
        return "Type: " + getVehicleType()
                + ", Vehicle number: " + vehicleNumber
                + ", Manufacturer: " + manufacturer
                + ", Year: " + year
                + ", Color: " + color
                + ", " + owner
                + ", " + getSpecificDetails();
    }
}
