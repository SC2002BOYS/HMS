package Controller;
import Model.MedicalRecord;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocUpdate implements IUpdate{
    private String newDiagnosis;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DocUpdate(String newDiagnosis){
        this.newDiagnosis = newDiagnosis;
    }

    public void perform(MedicalRecord record){
        List<String> updatedDiagnosisList = new ArrayList<>(record.getPastDiagnosis());
        updatedDiagnosisList.add(this.newDiagnosis);
        record.setPastDiagnosis(updatedDiagnosisList);
        saveUpdatedRecord(record);
    }

    private void saveUpdatedRecord(MedicalRecord updatedRecord) {
        String csvFile = "HMS/External Data/MedicalRecord.csv";
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
                    List<String> currentDiagnosisList = new ArrayList<>(Arrays.asList(fields[6].replace("\"", "").split(";")));
                    currentDiagnosisList.add(this.newDiagnosis); // Add the new diagnosis
                    fields[6] = "\"" + String.join(";", currentDiagnosisList) + "\""; // Join with semicolons and add quotes
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