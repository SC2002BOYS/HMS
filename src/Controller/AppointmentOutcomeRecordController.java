package Controller;
import Model.Appointment;
import Model.AppointmentOutcomeRecord;
import Model.Patient;
import Model.Schedule;
import Type.PrescriptionStatus;
import Type.ServiceType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class AppointmentOutcomeRecordController {
    
    //attributes
    private static final String CSV_FILE_PATH = "External Data/AppointmentOutcomeRecord.csv";

    private Schedule schedule;
    private ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords;

    public AppointmentOutcomeRecordController(Schedule schedule){
        this.schedule = schedule;
        this.appointmentOutcomeRecords = new ArrayList<AppointmentOutcomeRecord>();
    }

    public void addRecord(Patient patient, AppointmentOutcomeRecord outcomeRecord){

        patient.getAppointmentOutcomeRecords().add(outcomeRecord);

        appendToCSV(patient.getUserID(), outcomeRecord);

    }

    public void editAppointmentOutcomeRecord(){
        ArrayList<Appointment> appointments = schedule.getAppointments();
        ScheduleController scheduleController = new ScheduleController(schedule);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        scheduleController.viewUpcomingAppointments();
        System.out.println();
        if(appointments.isEmpty())
        {
            System.out.println("No appointments currently.");
            return;
        }

        System.out.println("Choose appointment to record outcome: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        Appointment currentAppt = appointments.get(choice-1);
        System.out.println("You chose:" + currentAppt.getPatientID() + " , " + currentAppt.getStartTime().format(formatter) + " to " + currentAppt.getEndTime().format(formatter));

        LocalDate date = currentAppt.getStartTime().toLocalDate();

        System.out.println("Enter Service Type (CONSULTATION, XRAY, BLOOD_TEST, THERAPY):");
        String input = scanner.nextLine();
        ServiceType type = null;
        try {
            // Convert the user input to an enum value
            type = ServiceType.valueOf(input.toUpperCase());
            System.out.println("You chose: " + type);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type entered.");
        }

        ArrayList<String> prescriptions = new ArrayList<String>();
        ArrayList<PrescriptionStatus> prescriptionStatuses = new ArrayList<PrescriptionStatus>();

        while(true){
            System.out.println("Enter prescription (type 'STOP' to finish): ");
            input = scanner.nextLine();
            if(input.toUpperCase().equals("STOP")){
                break;
            }
            prescriptions.add(input);
            prescriptionStatuses.add(PrescriptionStatus.PENDING);
        }

        System.out.println("Enter Consultation Notes: ");
        String notes = scanner.nextLine();

        AppointmentOutcomeRecord appointmentOutcomeRecord = new AppointmentOutcomeRecord(date,type,prescriptions,prescriptionStatuses,notes);
        Patient patient = new Patient(currentAppt.getPatientID(), CSVReader.getPassword("External Data/Users.csv", currentAppt.getPatientID()), CSVReader.getGender("External Data/Users.csv", currentAppt.getPatientID()), CSVReader.getAge("External Data/Users.csv", currentAppt.getPatientID()),new PatientMenuHandler(new PatientScheduleHandler()));

        addRecord(patient,appointmentOutcomeRecord);

        System.out.println("Apppointment Outcome Recorded for "+currentAppt.getPatientID());

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
