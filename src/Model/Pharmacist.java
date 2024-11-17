package Model;
import Controller.MenuHandler;
import Type.Gender;
import Type.Role;
import java.util.*;


public class Pharmacist extends User{
    private Inventory inventory;
    private ArrayList<Request> requests;
    private final MenuHandler menuHandler;

    public Pharmacist(String userID, String password, Gender gender, String age, MenuHandler menuHandler) {
        super(userID, password, Role.PHARMACIST, gender, age);
        this.inventory = new Inventory(); // Example initialization
        this.requests = new ArrayList<>(); // Example initialization
        this.menuHandler = menuHandler;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    public void runModule() {
        boolean exit = false;
        while (!exit) {
            menuHandler.displayMenu();
            System.out.print("Enter your choice: ");
            int choice = new Scanner(System.in).nextInt();

            if (choice == 6) {
                exit = true;
            } else {
                menuHandler.handleMenuOption(choice, this);
            }
        }
    }
}
