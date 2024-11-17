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
import Controller.StaffController;
import Controller.AppointmentController;
import Controller.InventoryController;
import Controller.ReplenishmentController;
import java.util.Scanner;

public class Administrator extends User{
    //attributes
    private final AdministratorMenu menu = new AdministratorMenu();
    private final HospitalStaffMenu staffMenu = new HospitalStaffMenu();
    //the controllers to do the actions
    private StaffController staffController;
    private AppointmentController appointmentController;
    //private InventoryController inventoryController;
    private ReplenishmentController replenishmentController;

    //constructor
    public Administrator(String userID, String userPass, Role role, Gender gender, String age, StaffController staffController, AppointmentController appointmentController, ReplenishmentController replenishmentController) {
        super(userID,userPass,role, gender, age);
        this.staffController = staffController;
        this.appointmentController = appointmentController;
        //this.inventoryController = inventoryController;
        this.replenishmentController = replenishmentController;
    }

    //to print the administrator menu
    public void printMenu(){
        this.menu.printMenu();
    }
        public void runModule(int choice){
        switch(choice){
            case 1: this.staffMenu.printMenu();
                    staffController = new StaffController();
                    switch (choice) {
                        case 1: 
                                staffController.viewHospitalStaff();
                                break;
                        case 2:
                                staffController.addStaffMember();
                                break;
                        
                        case 3:staffController.updateStaffMember();
                                break;

                        case 4: staffController.removeStaffMember();
                                break;

                        case 5: return;
                    
                        default:
                                System.out.println("Invalid option. Please try again.");
                            break;
                    }

                    break;

            case 2: appointmentController = new AppointmentController();
                    appointmentController.viewAppointmentsDetails();
                    break;
            
            /*
            case 3 : InventoryController inventoryController = new InventoryController();
                    inventoryController.updateMedicationStockLevel("medicationId", 10);
                    break;
            */
            case 4: ReplenishmentController replenishmentController = new ReplenishmentController();
                    replenishmentController.approveReplenishmentRequest("requestId");

                    break;
        }
    }


}
