package View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewPatientRecords {

    private static final String APPOINTMENTS_FILE = "External Data/Appointments.csv";
    private static final String MEDICAL_RECORDS_FILE = "External Data/medicalRecord.csv";

    public ViewPatientRecords(String doctorName) {
        List<String> patientIDs = getPatientsUnderDoctor(doctorName);
        if (!patientIDs.isEmpty()) {
            printMedicalRecords(patientIDs);
        } else {
            System.out.println("No confirmed patients found for doctor: " + doctorName);
        }
    }

    // Method to get patient IDs for a given doctor name with CONFIRMED status
    private List<String> getPatientsUnderDoctor(String doctorName) {
        List<String> patientIDs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_FILE))) {
            String line;

            // Read each line from Appointments.csv
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String patientID = parts[0].trim();
                    String doctor = parts[1].trim();
                    String status = parts[4].trim();

                    // Check if the doctor's name matches and the status is CONFIRMED
                    if (doctor.equalsIgnoreCase(doctorName) && status.equalsIgnoreCase("CONFIRMED")) {
                        patientIDs.add(patientID);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the appointments file: " + e.getMessage());
        }
        return patientIDs;
    }

    // Method to print medical records for the given patient IDs
    private void printMedicalRecords(List<String> patientIDs) {
        System.out.println("Medical Records for Patients:");
        System.out.println("=====================================");

        try (BufferedReader reader = new BufferedReader(new FileReader(MEDICAL_RECORDS_FILE))) {
            String line;

            // Read each line from medicalRecord.csv
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // Split by comma, keeping empty fields

                if (parts.length >= 8) {
                    String patientID = parts[0].trim();

                    // Check if the patientID matches any in the list
                    if (patientIDs.contains(patientID)) {
                        String fullName = parts[1].trim();
                        String dob = parts[2].trim();
                        String gender = parts[3].trim();
                        String contactNumber = parts[4].trim();
                        String bloodType = parts[5].trim();
                        String conditions = parts[6].trim().replace(";", ", ");
                        String email = parts[7].trim();

                        // Display the medical record
                        System.out.println("Patient ID: " + patientID);
                        System.out.println("Full Name: " + fullName);
                        System.out.println("Date of Birth: " + dob);
                        System.out.println("Gender: " + gender);
                        System.out.println("Contact Number: " + contactNumber);
                        System.out.println("Blood Type: " + bloodType);
                        System.out.println("Medical Conditions: " + conditions);
                        System.out.println("Email: " + email);
                        System.out.println("-------------------------------------");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the medical records file: " + e.getMessage());
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example: View patient records for a specific doctor
        new ViewPatientRecords("JaredKong");
    }
}
