package Controller;

import Controller.IUpdate;
import Model.MedicalRecord;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PatientUpdate implements IUpdate {
    //patient calls the perform method to update the non medical info
    private String newEmail;
    private int newContactInfo;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public PatientUpdate(String newEmail, int newContactInfo){
        this.newEmail = newEmail;
        this.newContactInfo = newContactInfo;
    }
    public void perform(MedicalRecord record){
        record.setContactInfo(newContactInfo);
        record.setEmail(newEmail);
        saveUpdatedRecord(record);
    }

    private void saveUpdatedRecord(MedicalRecord updatedRecord) {
        String csvFile = "External Data/MedicalRecord.csv";
        List<String[]> records = new ArrayList<>();
        boolean recordUpdated = false; // Flag to track if the record was updated

        // Read records from the CSV file and store each row as an array
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                // Check if this line is the header or the target record
                if (fields[0].equals("patientID")) {
                    // This is the header line; add it to the list as-is
                    records.add(fields);
                } else if (fields[0].equals(updatedRecord.getPatientID())) {
                    // This is the record to update
                    System.out.println("Updating record for patientID: " + fields[0]);
                    fields[4] = String.valueOf(newContactInfo); // Update contact info
                    fields[7] = newEmail; // Update email
                    records.add(fields); // Add updated record
                    recordUpdated = true;
                } else {
                    // Other records remain unchanged
                    records.add(fields);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!recordUpdated) {
            System.out.println("No matching record found for patientID: " + updatedRecord.getPatientID());
        }

        // Write the updated records back to the CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (String[] record : records) {
                writer.write(String.join(",", record));
                writer.write("\n"); // Move to the next line
            }
            System.out.println("File updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}