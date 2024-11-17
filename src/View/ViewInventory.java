package View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewInventory {

    private static final String INVENTORY_FILE = "HMS/External Data/Inventory.csv";

    public void displayInventory() {
        System.out.println("Current Inventory:");
        System.out.println("------------------");

        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;

            // Read each line from the inventory.csv file
            while ((line = reader.readLine()) != null) {
                // Split each line into medicine name and stock level
                String[] parts = line.split(",");

                if (parts.length == 2) { // Ensure the line has two parts
                    String medicineName = parts[0].trim();
                    String stockLevel = parts[1].trim();

                    // Print the inventory details
                    System.out.println(medicineName + ": " + stockLevel);
                } else {
                    System.err.println("Invalid line in inventory file: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the inventory file: " + e.getMessage());
        }
    }

}
