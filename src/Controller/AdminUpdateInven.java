package Controller;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUpdateInven {

    private static final String INVENTORY_FILE = "External Data/Inventory.csv";
    private List<String> listOfMedicine;

    // Constructor to initialize the list of medicines
    public AdminUpdateInven(List<String> listOfMedicine) {
        this.listOfMedicine = listOfMedicine;
    }

    // Method to update inventory
    public void updateInventory() {
        Map<String, Integer> inventory = loadInventory();

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty or could not be loaded.");
            return;
        }

        // Update stocks of medicines in the list
        for (String medicine : listOfMedicine) {
            if (inventory.containsKey(medicine)) {
                inventory.put(medicine, 100); // Set stock to 100
                System.out.println("Updated stock for " + medicine + " to 100.");
            } else {
                System.out.println("Medicine " + medicine + " not found in inventory.");
            }
        }

        // Save the updated inventory back to the CSV file
        saveInventory(inventory);
    }

    // Method to load inventory data from the CSV file
    private Map<String, Integer> loadInventory() {
        Map<String, Integer> inventory = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String medicineName = parts[0].trim();
                    int stock = Integer.parseInt(parts[1].trim());
                    inventory.put(medicineName, stock);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
        return inventory;
    }

    // Method to save updated inventory data back to the CSV file
    private void saveInventory(Map<String, Integer> inventory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            System.out.println("Inventory updated successfully.");
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }
}
