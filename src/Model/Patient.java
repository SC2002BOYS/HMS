package Model;
import java.util.ArrayList;
import Controller.CSVReader;
import View.PatientMenu;
import Type.Gender;
import Type.Role;

public class Patient extends User {

    //attributes
    private MedicalRecord medicalRecord;
    private ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private ArrayList<Appointment> appointments;
    private final PatientMenu menu = new PatientMenu();

    //constructor
    public Patient(String userID, String userPass, Role role, Gender gender, String age){
        super(userID, userPass, role, gender, age);
        this.medicalRecord = CSVReader.findMedicalRecordByPatientID("External Data/MedicalRecord.csv", userID);
        this.appointmentOutcomeRecords = CSVReader.getAppointmentOutcomeRecords("External Data/AppointmentOutcomeRecord.csv", userID);
        this.appointments = CSVReader.getAppointments("External Data/Appointments.csv", userID);
    }

    //to print the patient menu
    public void printMenu(){
        this.menu.printMenu();
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


}