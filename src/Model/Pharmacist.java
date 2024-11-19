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

    @Override
    public void runModule() {
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
                System.out.println("Invalid input! Please enter an integer.");
                scanner.nextLine(); // Clear the invalid input from the buffer
            }
        }
    }
}
