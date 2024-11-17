package Controller;
import Model.Patient;
import java.time.LocalDate;

public interface PrescriptionHandler {
    void dispenseMedication(Patient patient, LocalDate date);
    void viewPatientRecords(Patient patient);
    void requestPrescription(LocalDate date, String prescription);
}
