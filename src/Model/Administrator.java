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
// ● Administrator approves a replenishment request from a pharmacist.
// ● Verify that the request status changes to "approved," and the medication
// inventory is updated accordingly.


import Type.Role;
import View.AdministratorMenu;
import Controller.StaffController;
import Controller.AppointmentController;
import Controller.InventoryController;
import Controller.ReplenishmentController;

import java.util.ArrayList;

public class Administrator extends User{
    public Administrator(String userID, String userPass, Role role) 
    {
        super(userID, userPass, role);
    }
    //attributes
    private final AdministratorMenu menu = new AdministratorMenu();
    //the controllers to do the actions
    private StaffController staffControllerr;
    private AppointmentController appointmentController;
    private InventoryController inventoryController;
    private ReplenishmentController replenishmentController;

    //constructor
    public Administrator(String userID, String userPass, Role role, StaffController staffControllerr, AppointmentController appointmentController, InventoryController inventoryController, ReplenishmentController replenishmentController) {
        super(userID, userPass, role);
        this.staffControllerr = staffControllerr;
        this.appointmentController = appointmentController;
        this.inventoryController = inventoryController;
        this.replenishmentController = replenishmentController;
    }

    //to print the administrator menu
    public void printMenu(){
        this.menu.printMenu();
    }
        public void runModule(int choice){
        switch(choice){
            case 1: staffControllerr.viewHospitalStaff();
                    break;

            case 2: AppointmentController appointmentController = new AppointmentController();
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
