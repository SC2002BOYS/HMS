package Model;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.*;

import Controller.TimeGetter;
import Controller.UserActions;
import View.PharmacistMenu;
import View.AllViewAppointmentOutcome;
import Controller.CSVReader;
import Type.Role;
import Controller.EditMedStatus;

public class Pharmacist extends User implements UserActions{
    private Inventory inventory;
    private ArrayList<Request> requests;
    private final PharmacistMenu menu = new PharmacistMenu();

    public Pharmacist(Inventory inventory,ArrayList<Request> requests){
        this.inventory = inventory;
        this.requests = requests;
    }

    public Pharmacist(){
        this.inventory = null;
        this.requests = null;
    }

    public void printMenu(){
        this.menu.printMenu();
    }

    public void runModule(int choice){
        switch(choice){
            case 1:
                Scanner sc = new Scanner(System.in);
                System.out.println("Please enter PatientID:");
                String patientID = sc.next();
                Patient patient = new Patient(patientID, CSVReader.getPassword("External Data/Users.csv", patientID), Role.PATIENT);
                AllViewAppointmentOutcome.staffViewAppointmentOutcome(patient.getAppointmentOutcomeRecords());

            case 2:
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Please enter PatientID:");
                String patientID1 = sc1.next();
                Patient patient1 = new Patient(patientID1, CSVReader.getPassword("External Data/Users.csv", patientID1), Role.PATIENT);
                LocalDate date = TimeGetter.getTime();
                EditMedStatus.dispenseMed(patient1, date);


        }
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public void setRequests(ArrayList<Request> requests){
        this.requests = requests;
    }

    public ArrayList<Request> getRequests(){
        return this.requests;
    }

}
