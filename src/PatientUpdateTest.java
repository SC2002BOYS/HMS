import Controller.*;
import Model.AppointmentOutcomeRecord;
import Model.Inventory;
import Model.Patient;
import Type.Role;
import View.ViewMedicalRecord;

import java.util.ArrayList;

public class PatientUpdateTest {
    public static void main(String[] args) {
        Patient patient = new Patient("JohnDoe", "password123", Role.PATIENT);

    /*
        System.out.println(patient.getMedicalRecord().getPastDiagnosis());
        System.out.println(patient.getMedicalRecord().getEmail());
        UpdateController updateController = new UpdateController();
        IUpdate patientUpdate = new PatientUpdate("ScHuiCall@gmail.com", 898231321);
        updateController.update(patientUpdate, patient.getMedicalRecord());
        IUpdate docUpdate = new DocUpdate("asthma");
        updateController.update(docUpdate, patient.getMedicalRecord());
        System.out.println(patient.getMedicalRecord().getPastDiagnosis());
        System.out.println(patient.getMedicalRecord().getEmail());
        ViewMedicalRecord viewMedicalRecord = new ViewMedicalRecord();
        viewMedicalRecord.displayRecord(patient.getMedicalRecord());
        /*
     */
        Inventory inventory = Inventory.initializeFromCSV("HMS/External Data/Inventory.csv");
        System.out.println("Medication Names: " + inventory.getMedicationName());
        System.out.println("Medication Counts: " + inventory.getMedicationCount());
        PharmUpdateInven updater = new PharmUpdateInven();
        ArrayList<AppointmentOutcomeRecord> records = patient.getAppointmentOutcomeRecords();
        AppointmentOutcomeRecord firstRecord = records.getFirst();
        updater.perform(firstRecord);
        inventory.getMedicationCount();
    }
}
