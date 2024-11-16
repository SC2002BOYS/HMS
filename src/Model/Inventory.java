package Model;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<String> medicationName;
    private ArrayList<Integer> medicationCount;

    public Inventory(ArrayList<String> medicationName, ArrayList<Integer> medicationCount){
        this.medicationName = medicationName;
        this.medicationCount = medicationCount;
    }

    public Inventory() {
        this.medicationName = null;
        this.medicationCount = null;
    }

    public void setMedicationName(ArrayList<String> medicationName){
        this.medicationName = medicationName;
    }

    public ArrayList<String> getMedicationName(){
        return this.medicationName;
    }

    public void setMedicationCount(ArrayList<String> medicationName){
        this.medicationName = medicationName;
    }

    public ArrayList<Integer> getMedicationCount(){
        return this.medicationCount;
    }
}
