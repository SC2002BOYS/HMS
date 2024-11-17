package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AppointmentController {
    // Method to view appointments details
    
    private static final String CSV_FILE_PATH = "HMS/External Data/Appointments.csv";

    public void viewAppointmentsDetails() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
        }  
    }
}
