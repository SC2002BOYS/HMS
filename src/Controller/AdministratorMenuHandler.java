package Controller;
import java.util.*;
import Model.User;
import View.*;


public class AdministratorMenuHandler implements MenuHandler {

    private final Menu HospitalStaffMenu;

    //constructor
    public AdministratorMenuHandler(Menu HospitalStaffMenu)
    {
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
        IStaffController staffController = new StaffController();
        IAppointmentController appointmentController = new AppointmentController();
        ReplenishmentController replenishmentController = new ReplenishmentController();
        Menu pendingMenu = new PendingMenu();
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
                            System.out.println();
                            break;

                        case 2:staffController.addStaffMember();
                            System.out.println();
                            break;

                        case 3:staffController.updateStaffMember();
                            System.out.println();
                            break;

                        case 4:staffController.removeStaffMember();
                            System.out.println();
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
                System.out.println();
                break;

            case 3:
            while (true) {
                Menu mediciMenu = new MedicineInventoryMenu();
                mediciMenu.printMenu();
                System.out.print("Enter your choice: ");
                int Choice = scanner.nextInt();
                switch (Choice) {
                    case 1:
                        PrescriptionHandler prescriptionHandler = new PrescriptionServiceHandler();
                        prescriptionHandler.viewInventory();
                        System.out.println();
                        break;
                    case 2:
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Enter the medication name:");
                        String medicationName = sc.next();
                        IAdminUpdateInven adminUpdate = new AdminUpdateInven(medicationName);
                        adminUpdate.updateInventory();
                        System.out.println();
                        break;
                    case 3:
                        break; // Break out of the inner switch case
                    default:
                        System.out.println("Invalid option. Please try again.");
                        continue;
                }
                if (Choice == 3) {
                    break; // Break out of the while loop
                }
            }
            break;

            case 4:

                if (replenishmentController.printPendingRequests()) {
                    replenishmentController.approveReplenishmentRequest();
                }
                System.out.println();
                break;

            default:
                System.out.println("Invalid option. Please try again.");
               
        }
    }
    
}
