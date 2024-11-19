package View;

public class MedicineInventoryMenu implements Menu {
    @Override
    public void printMenu() {
        System.out.println("Medicine Inventory Menu");
        System.out.println("1. View Inventory");
        System.out.println("2. Update Inventory");
        System.out.println("3. Back to administrator menu");
    }
    
}
