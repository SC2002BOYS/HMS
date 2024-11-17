package Controller;

import Model.AppointmentOutcomeRecord;
import Model.Inventory;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PharmUpdateInven implements IUpdateInven{
    private static final String INVENTORY_FILE = "HMS/External Data/Inventory.csv";
    private static final int LOW_STATUS = 5;

    public void perform(AppointmentOutcomeRecord appointmentOutcomeRecord) {
        // Get prescriptions from the appointment outcome record
        List<String> prescriptions = appointmentOutcomeRecord.getPrescriptions();
        if (prescriptions == null || prescriptions.isEmpty()) {
            System.out.println("No prescriptions to process.");
            return;
        }

        // Load inventory data
        Inventory inventory = Inventory.initializeFromCSV(INVENTORY_FILE);
        ArrayList<String> medicationName = inventory.getMedicationName();
        ArrayList<Integer> medicationCount = inventory.getMedicationCount();

        if (medicationName.isEmpty() || medicationCount.isEmpty()) {
            System.out.println("Inventory is empty or could not be loaded.");
            return;
        }

        // Process prescriptions
        for (String prescription : prescriptions) {
            int index = medicationName.indexOf(prescription);
            if (index != -1) {
                int currentStock = medicationCount.get(index);
                if (currentStock >= LOW_STATUS) {
                    medicationCount.set(index, currentStock - LOW_STATUS); // Reduce stock by 5
                    System.out.println("Updated stock for " + prescription + ": " + (currentStock - LOW_STATUS));
                } else {
                    System.out.println("Insufficient stock for " + prescription + ".");
                }
            } else {
                System.out.println("Medicine " + prescription + " not found in inventory.");
            }
        }

        // Save updated inventory back to the CSV file and update Inventory object
        saveInventory(medicationName, medicationCount);
        inventory.setMedicationName(medicationName);
        inventory.setMedicationCount(medicationCount);
    }

    // Method to save updated inventory data back to the CSV file
    private void saveInventory(ArrayList<String> medicationName, ArrayList<Integer> medicationCount) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (int i = 0; i < medicationName.size(); i++) {
                bw.write(medicationName.get(i) + "," + medicationCount.get(i));
                bw.newLine();
            }
            System.out.println("Inventory updated successfully.");
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }
}
