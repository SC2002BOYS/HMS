package Controller;
import java.util.*;

import Model.Administrator;
import Model.Patient;
import java.time.LocalDate;
import Controller.CSVReader;
import Model.User;
import Type.Role;
import Controller.MenuHandler;
import View.Menu;
import Controller.AdminUpdateInven;

public class AdministratorMenuHandler implements MenuHandler {

    private final IReplenishmentController replenishmentController;
    private final IAppointmentController appointmentController;
    private final IStaffController staffController;
    private final Menu HospitalStaffMenu;

    //constructor
    public AdministratorMenuHandler(IReplenishmentController replenishmentController,IAppointmentController appointmentController,IStaffController staffController, Menu HospitalStaffMenu)
    {
        this.replenishmentController = replenishmentController;
        this.appointmentController = appointmentController;
        this.staffController = staffController;
        this.HospitalStaffMenu = HospitalStaffMenu;
    }


    @Override
    public void displayMenu()
    {
        System.out.println("Administrator Menu");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View appointment details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Log out");
    }

    public void handleMenuOption(int choice, User user)
    {
        Scanner scanner = new Scanner(System.in);
        switch(choice)
        {
            case 1:
                while(true)
                {
                    HospitalStaffMenu.printMenu();
                    System.out.print("Enter your choice: ");
                    int staffChoice = scanner.nextInt();
                    switch (staffChoice) {
                        case 1:staffController.viewHospitalStaff();
                            break;

                        case 2:staffController.addStaffMember();
                            break;

                        case 3:staffController.updateStaffMember();
                            break;

                        case 4:staffController.removeStaffMember();
                            break;

                        case 5:
                            break;

                        default:
                            System.out.println("Invalid option. Please try again.");
                            continue;
                    }
                    if (staffChoice == 5) {
                        break; // 
                    }
                }
                break;
            case 2:
                appointmentController.viewAppointmentsDetails();
                break;

            case 3:
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the medication name:");
                String medicationName = sc.next();
                AdminUpdateInven adminUpdate = new AdminUpdateInven(medicationName);
                break;
            case 4:
                replenishmentController.approveReplenishmentRequest();
                break;

            default:
                System.out.println("Invalid option. Please try again.");
               
        }
    }
    
}
