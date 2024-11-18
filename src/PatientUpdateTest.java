import Controller.*;
import Model.AppointmentOutcomeRecord;
import Model.Inventory;
import Model.Patient;
import Type.Role;
import Type.Gender;
import View.ViewMedicalRecord;
import View.ViewPastApptOutcome;
import View.ViewPatientAppt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientUpdateTest {
    public static void main(String[] args) {
        Patient patient = new Patient("JohnDoe", "password123", Gender.MALE, "30", new PatientMenuHandler(new PatientScheduleHandler()));

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
        /*
        Inventory inventory = Inventory.initializeFromCSV("External Data/Inventory.csv");
        System.out.println("Medication Names: " + inventory.getMedicationName());
        System.out.println("Medication Counts: " + inventory.getMedicationCount());
        PharmUpdateInven updater = new PharmUpdateInven();
        ArrayList<AppointmentOutcomeRecord> records = patient.getAppointmentOutcomeRecords();
        AppointmentOutcomeRecord firstRecord = records.getFirst();
        updater.perform(firstRecord);
        inventory.getMedicationCount();
        List<String> medicinesToUpdate = Arrays.asList("Paracetamol", "Zyrtec", "Aspirin");
        AdminUpdateInven adminUpdate = new AdminUpdateInven(medicinesToUpdate);
        adminUpdate.updateInventory();
        */
        /*
        new ViewPatientAppt("JohnDoe");
        new ViewPastApptOutcome("JohnDoe");
        AdminUpdateInven adminUpdate = new AdminUpdateInven("Ibuprofen");
        adminUpdate.updateInventory();
        */
    }
}
