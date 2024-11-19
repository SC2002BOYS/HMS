package View;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewAllPatients{

    private static final String CSV_FILE_PATH = "External Data/Users.csv";

    
       public boolean printAllPatients() {
        boolean hasPatients = false;
        System.out.println("All Patients:");
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 5 && values[2].equalsIgnoreCase("PATIENT")) {
                    System.out.println(values[0]);
                    hasPatients = true;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
        }

        return hasPatients;
    }



        
    
    
}
