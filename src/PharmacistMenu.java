public class PharmacistMenu implements Menu{

    @Override
    public void printMenu() {
        System.out.println("Pharmacist Menu");
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update prescription status");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit replenishment request");
        System.out.println("5. Log out");
        System.out.println("6. Change password");
        System.out.println("Please enter option:");
    }
}
