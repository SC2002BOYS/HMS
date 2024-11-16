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

    @Override
    public void dispenseMedication(Patient patient, LocalDate date) {
        List<AppointmentOutcomeRecord> outcomeRecords = patient.getAppointmentOutcomeRecords();
        boolean isUpdated = false;

        for (AppointmentOutcomeRecord record : outcomeRecords) {
            if (record.getDate().equals(date)) {
                record.getPrescriptionStatus().replaceAll(status -> PrescriptionStatus.DISPENSED);
                record.getPrescriptions().forEach(prescription ->
                        System.out.println(prescription + " dispensed.")
                );
                isUpdated = true;
            }
        }

        if (isUpdated) {
            saveToCsv(patient.getUserID(), outcomeRecords);
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

    private void saveToCsv(String patientID, List<AppointmentOutcomeRecord> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (AppointmentOutcomeRecord record : records) {
                String csvLine = String.format("%s,%s,%s,%s,%s,%s",
                        patientID,
                        record.getDate(),
                        record.getServiceProvided(),
                        String.join(";", record.getPrescriptions()),
                        String.join(";", record.getPrescriptionStatus().stream().map(Enum::toString).toList()),
                        record.getConsultationNotes()
                );
                writer.write(csvLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }
}
