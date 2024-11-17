package Model;

import View.DoctorMenu;
import Type.Role;
import Type.Gender;
import java.util.ArrayList;


public class Doctor extends User{

    private ArrayList<MedicalRecord> medicalRecords;
    private Schedule schedule;

    private ArrayList<Patient> patients;
    private final DoctorMenu menu = new DoctorMenu();

    public Doctor(String userID,String userPass, Role role,Gender gender,String age, ArrayList<MedicalRecord> medicalRecords){
        super(userID, userPass, role, gender, age);
        this.medicalRecords = medicalRecords;
        this.schedule = new Schedule();
    }

    //To print the doctor menu
    public void printMenu(){
        this.menu.printMenu();
    }

    //Get medical records
    public ArrayList<MedicalRecord> getMedicalRecords() { return this.medicalRecords; }

    //Get schedule
    public Schedule getSchedule(){ return this.schedule; }

    //Get patients
    //public ArrayList<Patient> getPatients(){ return this.patients; }
}
