package Model;

import java.util.ArrayList;
import View.DoctorMenu;
import Type.Role;
import Type.Gender;

public class Doctor extends User{


    //attributes
    private ArrayList<MedicalRecord> medicalRecords;
    
    //private ArrayList<AppointmentRequest> appointmentRequests;
    //[comment out waiting for XY]private Schedule schedule;

    private ArrayList<Patient> patients;
    private final DoctorMenu menu = new DoctorMenu();

    public Doctor(String userID,String userPass, Role role, ArrayList<MedicalRecord> medicalRecords){
        super(userID, userPass, role, gender, role);
        //this.medicalRecords = medicalRecords;

    }

    //To print the doctor menu
    public void printMenu(){
        this.menu.printMenu();
    }

    //Get medical records
    public ArrayList<MedicalRecord> getMedicalRecords() { return this.medicalRecords; }

    //Get appointment requests
    //public ArrayList<AppointmentRequest> getAppointmentRequests(){ return this.appointmentRequests; }

    //Get schedule
    //[comment out waiting for XY]public Schedule getSchedule(){ return this.schedule; }

    //Get patients
    //public ArrayList<Patient> getPatients(){ return this.patients; }
}

