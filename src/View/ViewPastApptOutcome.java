package View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewPastApptOutcome extends ViewPatientDetails{
    private static final String APPOINTMENTS_FILE = "External Data/AppointmentOutcomeRecord.csv";

    public ViewPastApptOutcome(String userID){
        super(userID);
    }
    public void view() {
        System.out.println("Past Appointment Outcomes for User: " + userID);
        System.out.println("=====================================");

        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_FILE))) {
            String line;
            boolean hasAppointments = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // Split by comma, keeping empty fields

                if (parts.length >= 6 && parts[0].equals(userID)) {
                    hasAppointments = true;

                    String date = parts[1].trim();
                    String serviceType = parts[2].trim();
                    String medications = parts[3].trim().replace(";", ", ");
                    String status = parts[4].trim().replace(";", ", ");
                    String notes = parts[5].trim();

                    System.out.println("Date of Appointment: " + date);
                    System.out.println("Service Type: " + serviceType);
                    System.out.println("Prescribed Medications: " + medications);
                    System.out.println("Status: " + status);
                    System.out.println("Consultation Notes: " + notes);
                    System.out.println("-------------------------------------");
                }
            }

            if (!hasAppointments) {
                System.out.println("No appointment outcomes found for user: " + userID);
            }

        } catch (IOException e) {
            System.err.println("Error reading the appointment outcomes file: " + e.getMessage());
        }
    }
}
