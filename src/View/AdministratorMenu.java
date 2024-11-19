package View;

public class AdministratorMenu implements Menu{

    @Override
    public void printMenu() {
        System.out.println("Administrator Menu");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View appointment details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Update Medical Record");
        System.out.println("6. Log out");
    }
    
}
