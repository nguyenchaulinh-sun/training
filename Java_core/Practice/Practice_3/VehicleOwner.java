package Practice_3;

public class VehicleOwner {
    private final String idNumber;
    private final String fullName;
    private final String email;

    public VehicleOwner(String idNumber, String fullName, String email) {
        if (idNumber == null || !idNumber.matches("\\d{12}")) {
            throw new IllegalArgumentException("Owner ID must contain exactly 12 digits.");
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.idNumber = idNumber;
        this.fullName = fullName.trim();
        this.email = email;
    }

    public String getIdNumber() { return idNumber; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Owner ID: " + idNumber + ", Name: " + fullName + ", Email: " + email;
    }
}
