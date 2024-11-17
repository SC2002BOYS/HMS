package View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewPastApptOutcome {
    private static final String APPOINTMENTS_FILE = "External Data/AppointmentOutcomeRecord.csv";
    public ViewPastApptOutcome(String userID){
        this.viewPastApptOutcome(userID);
    }

    private void viewPastApptOutcome(String userID) {
        System.out.println("Past Appointment Outcomes for user: " + userID);
        System.out.println("------------------------------------");

        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_FILE))) {
            String line;

            boolean hasAppointments = false;

            // Read each line from the Appointments.csv file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Check if the first index matches the userID
                if (parts.length > 0 && parts[0].equals(userID)) {
                    hasAppointments = true;
                    System.out.println(line); // Print the matching line
                }
            }

            if (!hasAppointments) {
                System.out.println("No appointment outcome found for user: " + userID);
            }

        } catch (IOException e) {
            System.err.println("Error reading the appointments outcome file: " + e.getMessage());
        }
    }
}
