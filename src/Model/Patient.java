package Model;
import java.util.ArrayList;
import View.PatientMenu;
import Type.Role;

public class Patient extends User {

    //attributes
    private MedicalRecord medicalRecord;
    private ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private ArrayList<Appointment> appointments;
    private final PatientMenu menu = new PatientMenu();



    //constructor
    public Patient(String userID,String userPass, Role role, MedicalRecord medicalRecord, ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords, ArrayList<Appointment> appointments){
        super(userID, userPass, role);
        this.medicalRecord = medicalRecord;
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.appointments = appointments;
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