package View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewPatientAppt extends ViewPatientDetails{
    private static final String APPOINTMENTS_FILE = "External Data/Appointments.csv";

    public ViewPatientAppt(String userID){
        super(userID);
    }
    public void view() {
        System.out.println("Appointments for User: " + userID);
        System.out.println("=====================================");

        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_FILE))) {
            String line;
            boolean hasAppointments = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 5 && parts[0].equals(userID)) {
                    hasAppointments = true;

                    String doctor = parts[1].trim();
                    String startDateTime = parts[2].trim();
                    String endDateTime = parts[3].trim();
                    String status = parts[4].trim();

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
