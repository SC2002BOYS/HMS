package Model;
import Controller.MenuHandler;
import Type.Role;
import java.util.*;


public class Pharmacist extends User{
    private Inventory inventory;
    private ArrayList<Request> requests;
    private final MenuHandler menuHandler;

    public Pharmacist(String userID, String password, MenuHandler menuHandler) {
        super(userID, password, Role.PHARMACIST);
        this.inventory = new Inventory(); // Example initialization
        this.requests = new ArrayList<>(); // Example initialization
        this.menuHandler = menuHandler;
    }

    public Pharmacist(MenuHandler menuHandler) {
        this.menuHandler = menuHandler;
        this.inventory = null;
        this.requests = null;
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

            if (choice == 5) {
                exit = true;
            } else {
                menuHandler.handleMenuOption(choice, this);
            }
        }
    }
}
