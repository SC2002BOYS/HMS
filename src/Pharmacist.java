import java.util.ArrayList;

public class Pharmacist extends User{
    private ArrayList<Patient> patients;
    private Inventory inventory;
    private ArrayList<Request> requests;
    private PharmacistMenu menu;

    public Pharmacist(ArrayList<Patient> patients,Inventory inventory,ArrayList<Request> requests,PharmacistMenu menu){
        this.patients = patients;
        this.inventory = inventory;
        this.requests = requests;
        this.menu = menu;
    }

    public void PrintMenu(){
        this.menu.printMenu();
    }

    public void setPatients(ArrayList<Patient> patients){
        this.patients = patients;
    }

    public ArrayList<Patient> getPatients(){
        return this.patients;
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
