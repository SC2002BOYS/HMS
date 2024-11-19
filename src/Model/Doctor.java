package Model;

import Controller.DoctorMenuHandler;
import Controller.MenuHandler;
import View.DoctorMenu;
import Type.Role;
import Type.Gender;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Doctor extends User{


    private Schedule schedule;
    private final MenuHandler menuHandler;

    private ArrayList<Patient> patients;
    private final DoctorMenu menu = new DoctorMenu();

    public Doctor(String userID,String userPass,Gender gender,String age) {
        super(userID, userPass, Role.DOCTOR, gender, age);
        this.schedule = new Schedule(userID);
        this.menuHandler = new DoctorMenuHandler(this.schedule);
    }

    //Get schedule
    public Schedule getSchedule(){ return this.schedule; }

    //Get patients
    //public ArrayList<Patient> getPatients(){ return this.patients; }

    public void runModule() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            menuHandler.displayMenu();
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt(); // Read user input

                if (choice == 10) {
                    exit = true; // Exit condition
                } else {
                    menuHandler.handleMenuOption(choice, this); // Handle other options
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid choice.");
                scanner.nextLine(); // Clear the invalid input from the buffer
            }
        }
    }
}
