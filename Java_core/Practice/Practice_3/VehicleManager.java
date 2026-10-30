package Practice_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VehicleManager {
    private final List<Vehicle> vehicles = new ArrayList<Vehicle>();

    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle == null) throw new IllegalArgumentException("Vehicle cannot be null.");
        if (findByVehicleNumber(vehicle.getVehicleNumber()) != null) return false;

        // A CMND/CCCD belongs to one owner record. The same owner may own multiple vehicles.
        VehicleOwner existingOwner = findOwnerById(vehicle.getOwner().getIdNumber());
        if (existingOwner != null) {
            if (!existingOwner.getFullName().equals(vehicle.getOwner().getFullName())
                    || !existingOwner.getEmail().equalsIgnoreCase(vehicle.getOwner().getEmail())) {
                throw new IllegalArgumentException("This owner ID is already registered with different owner details.");
            }
        }
        vehicles.add(vehicle);
        return true;
    }

    public Vehicle findByVehicleNumber(String number) {
        if (number == null) return null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleNumber().equalsIgnoreCase(number.trim())) return vehicle;
        }
        return null;
    }

    public List<Vehicle> findByOwnerId(String idNumber) {
        List<Vehicle> result = new ArrayList<Vehicle>();
        if (idNumber == null) return result;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getOwner().getIdNumber().equals(idNumber.trim())) result.add(vehicle);
        }
        return result;
    }

    public int deleteByManufacturer(String manufacturer) {
        if (!Vehicle.isAllowedManufacturer(manufacturer)) {
            throw new IllegalArgumentException("Unknown manufacturer.");
        }
        int before = vehicles.size();
        final String target = manufacturer.trim();
        vehicles.removeIf(v -> v.getManufacturer().equalsIgnoreCase(target));
        return before - vehicles.size();
    }

    public Map<String, Integer> countByManufacturer() {
        Map<String, Integer> counts = new LinkedHashMap<String, Integer>();
        counts.put("Honda", 0);
        counts.put("Yamaha", 0);
        counts.put("Toyota", 0);
        counts.put("Suzuki", 0);
        for (Vehicle vehicle : vehicles) {
            String key = vehicle.getManufacturer();
            counts.put(key, counts.get(key) + 1);
        }
        return counts;
    }

    public List<String> manufacturersWithMostVehicles() {
        Map<String, Integer> counts = countByManufacturer();
        int max = 0;
        for (int count : counts.values()) if (count > max) max = count;
        List<String> result = new ArrayList<String>();
        if (max == 0) return result;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == max) result.add(entry.getKey());
        }
        return result;
    }

    public Map<String, Integer> countByVehicleType() {
        Map<String, Integer> counts = new LinkedHashMap<String, Integer>();
        counts.put("Car", 0);
        counts.put("Motorbike", 0);
        counts.put("Truck", 0);
        for (Vehicle vehicle : vehicles) {
            String type = vehicle.getVehicleType();
            counts.put(type, counts.get(type) + 1);
        }
        return counts;
    }

    // Requirement 6 interpreted as sorting vehicle categories by their counts, descending.
    public List<Map.Entry<String, Integer>> sortTypesByCountDescending() {
        List<Map.Entry<String, Integer>> result =
                new ArrayList<Map.Entry<String, Integer>>(countByVehicleType().entrySet());
        Collections.sort(result, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return Integer.compare(b.getValue(), a.getValue());
            }
        });
        return result;
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<Vehicle>(vehicles);
    }

    public int size() { return vehicles.size(); }

    private VehicleOwner findOwnerById(String idNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getOwner().getIdNumber().equals(idNumber)) return vehicle.getOwner();
        }
        return null;
    }
}
