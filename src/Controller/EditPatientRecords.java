package Controller;

import Model.MedicalRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditPatientRecords{

    private static final String APPOINTMENTS_FILE = "External Data/Appointments.csv";
    private static final String MEDICAL_RECORDS_FILE = "External Data/medicalRecord.csv";
    private ArrayList<MedicalRecord> medialRecords;


    public EditPatientRecords(String doctorID){
        System.out.println("EDIT PATIENT MEDICAL RECORD");
        System.out.println("---------------------------");
        List<String> patientIDs = getPatientsUnderDoctor(doctorID);
        if(patientIDs.isEmpty())
        {
            System.out.println("There are no patients currently.");
            return;
        }
        System.out.println("Enter PatientID: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        MedicalRecord medicalRecord = CSVReader.findMedicalRecordByPatientID(MEDICAL_RECORDS_FILE,input);
        if(medicalRecord == null)
        {
            System.out.println("Patient not found. Please try again.");
            return;
        }
        System.out.println("Enter new diagnosis: ");
        input = scanner.nextLine();
        DocUpdate update =  new DocUpdate(input);
        update.perform(medicalRecord);
        System.out.println("Medical record updated successsfully.");
    }

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


}
