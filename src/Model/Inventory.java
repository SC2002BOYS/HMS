package Model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<String> medicationName;
    private ArrayList<Integer> medicationCount;

    public Inventory(ArrayList<String> medicationName, ArrayList<Integer> medicationCount){
        this.medicationName = medicationName;
        this.medicationCount = medicationCount;
    }

    public void setMedicationName(ArrayList<String> medicationName){
        this.medicationName = medicationName;
    }

    public ArrayList<String> getMedicationName(){
        return this.medicationName;
    }

    public void setMedicationCount(ArrayList<Integer> medicationCount){
        this.medicationCount = medicationCount;
    }

    public ArrayList<Integer> getMedicationCount(){
        return this.medicationCount;
    }

    public static Inventory initializeFromCSV(String csvFilePath) {
        ArrayList<String> medicationName = new ArrayList<>();
        ArrayList<Integer> medicationCount = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Skip empty or invalid lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split the line by commas
                String[] values = line.split(",");

                // Ensure the line has pairs of medication name and count
                if (values.length % 2 != 0) {
                    System.err.println("Invalid line format: " + line);
                    continue; // Skip this line
                }

                // Process pairs of medication names and counts
                for (int i = 0; i < values.length; i += 2) {
                    medicationName.add(values[i].trim()); // Medication name
                    medicationCount.add(Integer.parseInt(values[i + 1].trim())); // Medication count
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in file.");
            e.printStackTrace();
        }

        return new Inventory(medicationName, medicationCount);
    }
}
