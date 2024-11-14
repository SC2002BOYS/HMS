import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    public static MedicalRecord findMedicalRecordByPatientID(String filePath, String patientID) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Check if the current line matches the given patientID
                if (values[0].equals(patientID)) {
                    String name = values[1];
                    LocalDate dateOfBirth = LocalDate.parse(values[2]);
                    Gender gender = Gender.valueOf(values[3].toUpperCase());
                    int contactInfo = Integer.parseInt(values[4]);
                    BloodType bloodType = BloodType.valueOf(values[5].toUpperCase());

                    // Parse past diagnoses into a List
                    List<String> pastDiagnosis = List.of(values[6].split(";"));
                    String email = values[7];

                    // Return the MedicalRecord for the matched patient
                    return new MedicalRecord(patientID, name, dateOfBirth, gender, contactInfo, bloodType, pastDiagnosis, email);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // If no matching patientID found
    }

    public static ArrayList<Appointment> getAppointments(String filePath, String patientID) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Match patientID with the external identifier (assuming values[0] is patient ID)
                if (values[0].equals(patientID)) {
                    String doctor = values[1];
                    LocalDateTime startTime = LocalDateTime.parse(values[2]);
                    LocalDateTime endTime = LocalDateTime.parse(values[3]);
                    AppointmentStatus status = AppointmentStatus.valueOf(values[4].toUpperCase());

                    appointments.add(new Appointment(doctor, startTime, endTime, status));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public static ArrayList<AppointmentOutcomeRecord> getAppointmentOutcomeRecords(String filePath, String patientID) {
        ArrayList<AppointmentOutcomeRecord> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", 6); // Use limit to avoid splitting inside notes

                // Check for patientID match (assuming values[0] is patient ID)
                if (values[0].equals(patientID)) {
                    LocalDate date = LocalDate.parse(values[1]);
                    ServiceType serviceProvided = ServiceType.valueOf(values[2].toUpperCase());

                    // Split prescriptions and statuses by semicolon
                    List<String> prescriptions = Arrays.asList(values[3].split(";"));
                    List<PrescriptionStatus> prescriptionStatus = new ArrayList<>();
                    for (String status : values[4].split(";")) {
                        prescriptionStatus.add(PrescriptionStatus.valueOf(status.toUpperCase()));
                    }

                    String consultationNotes = values[5]; // Notes are the last column

                    records.add(new AppointmentOutcomeRecord(date, serviceProvided, prescriptions, prescriptionStatus, consultationNotes));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
