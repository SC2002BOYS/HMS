package Model;
import java.util.ArrayList;
import Controller.UserActions;
import View.PharmacistMenu;
import View.AllViewAppointmentOutcome;

public class Pharmacist extends User implements UserActions{
    private Inventory inventory;
    private ArrayList<Request> requests;
    private final PharmacistMenu menu = new PharmacistMenu();

    public Pharmacist(Inventory inventory,ArrayList<Request> requests){
        this.inventory = inventory;
        this.requests = requests;
    }

    public Pharmacist(){
        this.inventory = null;
        this.requests = null;
    }

    public void printMenu(){
        this.menu.printMenu();
    }

    public void runModule(int choice){
        switch(choice){
            case 1: AllViewAppointmentOutcome.staffViewAppointmentOutcome();
        }
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public void setRequests(ArrayList<Request> requests){
        this.requests = requests;
    }

    public ArrayList<Request> getRequests(){
        return this.requests;
    }

}
