package Controller;

import Model.MedicalRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminUpdateMed implements IUpdate {

    @Override
    public void perform(MedicalRecord record) {
        String csvFile = "External Data/MedicalRecord.csv";
        List<String[]> records = new ArrayList<>();
        boolean recordUpdated = false;

        // Read records from the CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                // Add a "status" column to the header if it doesn't exist
                if (fields[0].equals("patientID")) {
                    if (fields.length < 9) {
                        fields = addStatusColumn(fields);
                    }
                    records.add(fields);
                } else if (fields[0].equals(record.getPatientID())) {
                    // This is the record to deactivate
                    System.out.println("Deactivating record for patientID: " + fields[0]);

                    // Dynamically extend the array if "status" column is missing
                    if (fields.length < 9) {
                        fields = extendFields(fields, 9);
                    }

                    fields[8] = "INACTIVE"; // Set status to "INACTIVE"
                    records.add(fields);
                    recordUpdated = true;
                } else {
                    // Other records remain unchanged
                    if (fields.length < 9) {
                        fields = extendFields(fields, 9);
                    }
                    records.add(fields);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!recordUpdated) {
            System.out.println("No matching record found for patientID: " + record.getPatientID());
            return;
        }

        // Write the updated records back to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (String[] updatedRecord : records) {
                writer.write(String.join(",", updatedRecord));
                writer.newLine();
            }
            System.out.println("Record deactivated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to add a "status" column to the header
    private String[] addStatusColumn(String[] header) {
        String[] extendedHeader = new String[header.length + 1];
        System.arraycopy(header, 0, extendedHeader, 0, header.length);
        extendedHeader[header.length] = "status";
        return extendedHeader;
    }

    // Helper method to dynamically extend a record to the specified length
    private String[] extendFields(String[] fields, int newLength) {
        String[] extendedFields = new String[newLength];
        System.arraycopy(fields, 0, extendedFields, 0, fields.length);
        for (int i = fields.length; i < newLength; i++) {
            extendedFields[i] = ""; // Fill new fields with empty values
        }
        return extendedFields;
    }
}