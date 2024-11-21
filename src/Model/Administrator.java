package Model;
// 4. Administrator Actions
// Test Case 20: View and Manage Hospital Staff
// ● Administrators can view the list of hospital staff and add, update or remove staff
// members.
// ● Verify that the displayed list of staff is updated with any changes.
// Test Case 21: View Appointments Details
// ● Administrator views all appointments.
// ● Verify that the system displays a list of appointments including details like
// Patient ID, Doctor ID, status, and date/time.
// Test Case 22: View and Manage Medication Inventory
// ● Administrator updates the stock level of a medication.
// ● Verify that the medication's stock level is updated in the inventory.
// Test Case 23: Approve Replenishment Requests

import Type.Gender;
import Type.Role;
import View.AdministratorMenu;
import View.HospitalStaffMenu;
import Controller.MenuHandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrator extends User{
    //attributes
    private final MenuHandler menuHandler;
    private final AdministratorMenu menu = new AdministratorMenu();
    private final HospitalStaffMenu staffMenu = new HospitalStaffMenu();

    // The controllers to do the actions

    // Constructor
    public Administrator(String userID, String password, Gender gender, String age, MenuHandler menuHandler)
    {
        super(userID, password,Role.ADMIN, gender, age);
        this.menuHandler = menuHandler;

    }


    public void printStaffMenu()
    {
        staffMenu.printMenu();
    }

        @Override
        public void runModule()
        {
            boolean exit = false;
            Scanner scanner = new Scanner(System.in); // Create a single Scanner instance

            while (!exit) {
                menuHandler.displayMenu();
                System.out.print("Enter your choice: ");

                try {
                    int choice = scanner.nextInt(); // Attempt to read an integer

                    if (choice == 6) {
                        exit = true; // Exit the loop if the choice is 5
                    } else {
                        menuHandler.handleMenuOption(choice, this); // Handle other menu options
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid choice.");
                    scanner.nextLine(); // Clear the invalid input from the buffer
                }
            }
        }
        
    }

