package View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewPatientAppt {
    private static final String APPOINTMENTS_FILE = "External Data/Appointments.csv";
    public ViewPatientAppt(String userID){
        this.viewAppointments(userID);
    }

    private void viewAppointments(String userID) {
        System.out.println("Appointments for User: " + userID);
        System.out.println("=====================================");

        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_FILE))) {
            String line;

            boolean hasAppointments = false;

            // Read each line from the Appointments.csv file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Check if the first index matches the userID
                if (parts.length >= 5 && parts[0].equals(userID)) {
                    hasAppointments = true;

                    // Extract and format the fields
                    String doctor = parts[1].trim();
                    String startDateTime = parts[2].trim();
                    String endDateTime = parts[3].trim();
                    String status = parts[4].trim();

                    // Display the formatted output
                    System.out.println("Doctor: " + doctor);
                    System.out.println("Start Date & Time: " + startDateTime);
                    System.out.println("End Date & Time: " + endDateTime);
                    System.out.println("Status: " + status);
                    System.out.println("-------------------------------------");
                }
            }

            if (!hasAppointments) {
                System.out.println("No appointments found for user: " + userID);
            }

        } catch (IOException e) {
            System.err.println("Error reading the appointments file: " + e.getMessage());
        }
    }
}
