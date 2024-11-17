package Controller;
import java.util.*;
import Model.AppointmentOutcomeRecord;
import Model.Patient;
import Type.PrescriptionStatus;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PrescriptionServiceHandler implements PrescriptionHandler{
    private static final String CSV_FILE_PATH = "External Data/AppointmentOutcomeRecord.csv";
    private static final String REPENISHMENT_PATHH = "External Data/ReplenishRequest.csv";

    @Override
    public void dispenseMedication(Patient patient, LocalDate date) {
        ArrayList<AppointmentOutcomeRecord> outcomeRecords = patient.getAppointmentOutcomeRecords();
        boolean isUpdated = false;

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
            writeToCsv(patient.getUserID(), outcomeRecords);
            System.out.println("Prescriptions dispensed and updated.");
            System.out.println();
        } else {
            System.out.println("No records found for the specified date.");
        }
    }

    @Override
    public void viewPatientRecords(Patient patient) {
        if (patient.getAppointmentOutcomeRecords().isEmpty()) {
            System.out.println("No appointment outcome records found.");
        } else {
            patient.getAppointmentOutcomeRecords().forEach(record -> {
                System.out.println("Date: " + record.getDate());
                System.out.println("Service: " + record.getServiceProvided());
                System.out.println("Prescriptions: " + record.getPrescriptions());
                System.out.println("Prescription Status: " + record.getPrescriptionStatus());
                System.out.println("Notes: " + record.getConsultationNotes());
                System.out.println("----------------------------");
                System.out.println();
            });
        }
    }

    @Override
    public void requestPrescription(LocalDate date, String prescription){
        String status = "PENDING";

        String csvLine = date + "," + prescription + "," + status;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPENISHMENT_PATHH, true))) { // Open in append mode
            writer.write(csvLine);
            writer.newLine();
            System.out.println("Prescription request for " + prescription + " sent for approval.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }
    }

    private void writeToCsv(String patientID, ArrayList<AppointmentOutcomeRecord> outcomeRecords) {
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
