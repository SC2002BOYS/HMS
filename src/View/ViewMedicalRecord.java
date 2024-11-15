package View;
import Model.MedicalRecord;
import java.util.List;

public class ViewMedicalRecord {
    public void displayRecord(MedicalRecord record) {
        System.out.println("Patient ID: " + record.getPatientID());
        System.out.println("Name: " + record.getName());
        System.out.println("Date of Birth: " + record.getDateOfBirth());
        System.out.println("Gender: " + record.getGender());
        System.out.println("Contact Information: " + record.getContactInfo());
        System.out.println("Blood Type: " + record.getBloodType());

        // Printing past diagnoses
        List<String> pastDiagnosis = record.getPastDiagnosis();
        System.out.println("Past Diagnoses: ");
        if (pastDiagnosis != null && !pastDiagnosis.isEmpty()) {
            for (String diagnosis : pastDiagnosis) {
                System.out.println("  - " + diagnosis);
            }
        } else {
            System.out.println("  None");
        }

        System.out.println("Email: " + record.getEmail());
    }
}
