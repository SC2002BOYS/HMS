public class PatientUpdateTest {
    public static void main(String[] args) {
        Patient patient = new Patient("JohnDoe", "password123", Role.PATIENT,
                CSVReader.findMedicalRecordByPatientID("HMS/External Data/MedicalRecord.csv", "JohnDoe"), CSVReader.getAppointmentOutcomeRecords("HMS/External Data/AppointmentOutcomeRecord.csv", "JohnDoe"),
                CSVReader.getAppointments("HMS/External Data/Appointments.csv", "JohnDoe"));
        UpdateController updateController = new UpdateController();
        IUpdate patientUpdate = new PatientUpdate("ScHuiCallByReference@gmail.com", 898231321);
        updateController.update(patientUpdate, patient.getMedicalRecord());
    }
}
