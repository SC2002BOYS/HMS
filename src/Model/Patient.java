package Model;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.UpdatePassword;
import Controller.UserActions;
import View.PatientMenu;
import Type.Role;

public class Patient extends User implements UserActions{

    //attributes
    private MedicalRecord medicalRecord;
    private ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private ArrayList<Appointment> appointments;
    private final PatientMenu menu = new PatientMenu();
    private UpdatePassword passUpdate; //Object interface attribute PasswordController


    //constructor
    public Patient(String userID,String userPass, Role role, MedicalRecord medicalRecord, ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords, ArrayList<Appointment> appointments, UpdatePassword passUpdate){
        super(userID, userPass, role);
        this.medicalRecord = medicalRecord;
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.appointments = appointments;
        this.passUpdate = passUpdate;
    }

    //to print the patient menu
    public void printMenu(){
        this.menu.printMenu();
    }
    
    //method
    @Override
    public void runModule(int choice){
        switch(choice){
            case 8: // Password Update Option
                Scanner sc = new Scanner(System.in);
                System.out.print("New Password: ");
                String newPass = sc.nextLine();
                passUpdate.ChangePassword(newPass, this);
                System.out.println("New password: " + this.getUserPass());
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
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