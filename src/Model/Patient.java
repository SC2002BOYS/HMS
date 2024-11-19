package Model;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Controller.CSVReader;
import Controller.MenuHandler;
import Type.Gender;
import Type.Role;

public class Patient extends User {

    //attributes
    private MedicalRecord medicalRecord;
    private ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private ArrayList<Appointment> appointments;
    private final MenuHandler menuHandler;

    //constructor
    public Patient(String userID, String userPass, Gender gender, String age, MenuHandler menuHandler){
        super(userID, userPass, Role.PATIENT, gender, age);
        this.medicalRecord = CSVReader.findMedicalRecordByPatientID("External Data/MedicalRecord.csv", userID);
        this.appointmentOutcomeRecords = CSVReader.getAppointmentOutcomeRecords("External Data/AppointmentOutcomeRecord.csv", userID);
        this.appointments = CSVReader.getAppointments("External Data/Appointments.csv", userID);
        this.menuHandler = menuHandler;
    }

    //getters and setters
    public MedicalRecord getMedicalRecord(){
        return this.medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecord = medicalRecord;
    }

    public ArrayList<AppointmentOutcomeRecord> getAppointmentOutcomeRecords(){
        return this.appointmentOutcomeRecords;
    }

    public void setAppointmentOutcomeRecords(ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords){
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
    }

    public ArrayList<Appointment> getAppointments(){
        return this.appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments){
        this.appointments = appointments;
    }

    @Override
    public void runModule() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            menuHandler.displayMenu();
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt(); // Read user input

                if (choice == 9) {
                    exit = true; // Exit condition
                } else {
                    menuHandler.handleMenuOption(choice, this); // Handle other options
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid choice.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }


}