package Controller;
import Model.AppointmentOutcomeRecord;
import Model.Patient;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class AddAppointmentOutcomeRecord {
    
    //attributes
    private static final String CSV_FILE_PATH = "External Data/AppointmentOutcomeRecord.csv";

    public void addRecord(Patient patient, AppointmentOutcomeRecord outcomeRecord){

        patient.getAppointmentOutcomeRecords().add(outcomeRecord);

        appendToCSV(patient.getUserID(), outcomeRecord);

    }

    private void appendToCSV(String patientID, AppointmentOutcomeRecord record) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            // Convert the AppointmentOutcomeRecord into a CSV line

            String csvLine = patientID + "," +
                    record.getDate() + "," +
                    record.getServiceProvided() + "," +
                    String.join(";", record.getPrescriptions()) + "," +
                    record.getPrescriptionStatus().stream()
                            .map(Enum::name)
                            .reduce((a, b) -> a + ";" + b).orElse("") +
                    "," +
                    record.getConsultationNotes();

            // Write the line and add a newline
            bw.write(csvLine);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing to CSV file.");
        }
    }


}
