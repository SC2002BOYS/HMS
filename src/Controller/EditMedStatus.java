package Controller;

import java.util.*;
import Model.AppointmentOutcomeRecord;
import Model.Patient;
import Type.PrescriptionStatus;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EditMedStatus {

    private static final String CSV_FILE_PATH = "External Data/AppointmentOutcomeRecord.csv";

    public static void dispenseMed(Patient patient, LocalDate date) {
        // Get the list of appointment outcome records
        ArrayList<AppointmentOutcomeRecord> outcomeRecords = patient.getAppointmentOutcomeRecords();

        // Flag to track if any records were updated
        boolean isUpdated = false;

        // Iterate over records and update prescription statuses for the given date
        for (AppointmentOutcomeRecord record : outcomeRecords) {
            if (record.getDate().equals(date)) {
                List<PrescriptionStatus> statuses = record.getPrescriptionStatus();
                // Update all statuses to DISPENSED
                statuses.replaceAll(ignored -> PrescriptionStatus.DISPENSED);
                List<String> prescriptions = record.getPrescriptions();
                for (String prescription : prescriptions) {
                    System.out.println(prescription + " dispensed.");
                }
                record.setPrescriptionStatus(statuses); // Update the record
                isUpdated = true;
            }
        }

        if (isUpdated) {
            // Persist changes to the CSV file, including PatientID
            writeToCsv(patient.getUserID(), outcomeRecords);
            System.out.println("Prescription statuses updated successfully.");
        } else {
            System.out.println("No records found for the specified date.");
        }
    }

    // Method to write updated records to the CSV file
    private static void writeToCsv(String patientID, ArrayList<AppointmentOutcomeRecord> outcomeRecords) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (AppointmentOutcomeRecord record : outcomeRecords) {
                // Convert the AppointmentOutcomeRecord object to a CSV line
                StringBuilder csvLine = new StringBuilder();
                csvLine.append(patientID).append(","); // Include PatientID
                csvLine.append(record.getDate()).append(",");
                csvLine.append(record.getServiceProvided()).append(",");
                csvLine.append(String.join(";", record.getPrescriptions())).append(",");
                csvLine.append(String.join(";",
                        record.getPrescriptionStatus().stream()
                                .map(PrescriptionStatus::toString)
                                .toList()
                )).append(",");
                csvLine.append(record.getConsultationNotes());

                // Write the line to the file
                writer.write(csvLine.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }
    }
}
