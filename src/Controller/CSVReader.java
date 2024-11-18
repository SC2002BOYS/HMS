package Controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.AvailableSlot;
import Model.MedicalRecord;
import Model.User;
import Model.AppointmentOutcomeRecord;
import Model.Appointment;
import Type.Role;
import Type.Gender;
import Type.BloodType;
import Type.AppointmentStatus;
import Type.ServiceType;
import Type.PrescriptionStatus;



public class CSVReader {

    // CSV reader for Patient Medical Record
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

    // CSV reader for Appointments
    public static ArrayList<Appointment> getAppointments(String filePath, String patientID){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Appointment> appointments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Match patientID with the external identifier (assuming values[0] is patient ID)
                if (values[0].equals(patientID)) {
                    String doctor = values[1];
                    LocalDateTime startTime = LocalDateTime.parse(values[2],formatter);
                    LocalDateTime endTime = LocalDateTime.parse(values[3],formatter);
                    AppointmentStatus status = AppointmentStatus.valueOf(values[4].toUpperCase());

                    appointments.add(new Appointment(patientID,doctor, startTime, endTime, status));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // CSV reader for all Appointments entries
    public static ArrayList<String> getAllAppointmentsEntries(String filePath){
        ArrayList<String> allEntries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allEntries.add(line); // Add each line to the list
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return allEntries;
    }


    //CSV reader for appointment requests for doctors
    public static ArrayList<Appointment> getAppointmentRequests(String filePath, String doctorID){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Appointment> appointmentRequests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Match patientID with the external identifier (assuming values[0] is patient ID)
                if (values[1].equals(doctorID) && (AppointmentStatus.valueOf(values[4]) == AppointmentStatus.PENDING)) {
                    String patient = values[0];
                    String doctor = values[1];
                    LocalDateTime startTime = LocalDateTime.parse(values[2],formatter);
                    LocalDateTime endTime = LocalDateTime.parse(values[3],formatter);
                    AppointmentStatus status = AppointmentStatus.valueOf(values[4].toUpperCase());

                    appointmentRequests.add(new Appointment(patient, doctor, startTime, endTime, status));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointmentRequests;
    }


    // CSV reader for Appointment Outcome Records
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

    public static String getPassword(String filePath, String patientID){
        String line;
        String delimiter = ",";
        String storedUserPass = null;  // Initialize as null to handle when no match is found

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] userCred = line.split(delimiter);
                String storedUserID = userCred[0];

                // Check if the current user ID matches the given patient ID
                if (storedUserID.equals(patientID)) {
                    storedUserPass = userCred[1]; // Get the password for the matched user
                    break;  // Exit the loop once a match is found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return storedUserPass;  // Return the password if found, or null if not found
    }

    public static Gender getGender(String filePath, String patientID){

        String line;
        String delimiter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] userCred = line.split(delimiter);
                String storedUserID = userCred[0];
                String genderString = userCred[3];

                if (storedUserID.equals(patientID)) {
                    return Gender.valueOf(genderString.toUpperCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if the patient ID is not found
    }

    public static String getAge(String filePath, String patientID){

        String line;
        String delimiter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] userCred = line.split(delimiter);
                String storedUserID = userCred[0];
                String ageString = userCred[4]; // Assuming age is the 5th column

                if (storedUserID.equals(patientID)) {
                    return ageString;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if the patient ID is not found
    }

    // CSV reader for USERS
    public static List<User> readUsers(String filePath){

        String delimiter = ",";
        String line;
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                if (values.length == 5) {
                    String userID = values[0];
                    String userPass = values[1];
                    Role role = Role.valueOf(values[2].toUpperCase());
                    Gender gender = Gender.valueOf(values[3].toUpperCase());
                    String age = values[4];
                    users.add(new User(userID, userPass, role, gender, age));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }


    // CSV reader for Available Slots
    public static ArrayList<AvailableSlot> getAvailableSlots(String filePath, String doctorID){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<AvailableSlot> availableSlots = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Match patientID with the external identifier (assuming values[0] is patient ID)
                if (values[0].equals(doctorID)) {
                    LocalDateTime startTime = LocalDateTime.parse(values[1],formatter);
                    LocalDateTime endTime = LocalDateTime.parse(values[2],formatter);
                    availableSlots.add(new AvailableSlot(startTime, endTime));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return availableSlots;

    }

    public static ArrayList<String> getAllAvailSlotsEntries(String filePath){
        ArrayList<String> allEntries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allEntries.add(line); // Add each line to the list
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return allEntries;
    }
}
