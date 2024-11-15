package View;
import java.util.*;
import Model.Patient;
import Controller.CSVReader;
import Type.Role;
import Model.AppointmentOutcomeRecord;
import Model.IAppointmentOutcomeRecord;


public class AllViewAppointmentOutcome {

    private static final AppointmentOutcomeRecordView view = new AppointmentOutcomeRecordView();

    public static void staffViewAppointmentOutcome(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter PatientID:");
        String patientID = sc.next();
        Patient patient = new Patient(patientID, CSVReader.getPassword("HMS/External Data/Users.csv", patientID),
                    Role.PATIENT,
                    CSVReader.findMedicalRecordByPatientID("HMS/External Data/MedicalRecord.csv", patientID),
                    CSVReader.getAppointmentOutcomeRecords("HMS/External Data/AppointmentOutcomeRecord.csv", patientID),
                    CSVReader.getAppointments("HMS/External Data/Appointments.csv", patientID), null);

        List<AppointmentOutcomeRecord> outcomeRecords = patient.getAppointmentOutcomeRecords();

        // Check if there are any outcome records and print them
        if (outcomeRecords != null && !outcomeRecords.isEmpty()) {
            for (IAppointmentOutcomeRecord record : outcomeRecords) {
                view.printAppointmentOutcomeRecord(record);
            }
        } else {
            System.out.println("No appointment outcome records found for patient ID: " + patientID);
        }    }
}