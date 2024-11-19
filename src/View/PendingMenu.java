package View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PendingMenu implements Menu{

    private static final String CSV_FILE_PATH = "HMS/External Data/ReplenishRequest.csv";

    @Override
    public void printMenu()
        {

            try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3 && values[2].equalsIgnoreCase("PENDING")) {
                    System.out.println(line);
                }
            }
            } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
        }   

        }
    
    
}
