package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AppointmentController implements IAppointmentController {
    // Method to view appointments details
    
    private static final String APPOINTMENTS_CSV_FILE_PATH = "External Data/Appointments.csv";
    private static final String APPOINTMENT_OUTCOME_RECORD_CSV_FILE_PATH = "External Data/AppointmentOutcomeRecord.csv";

    public void viewAppointmentsDetails(String name) {

        boolean appointmentFound = false;
        boolean outcomeFound = false;
        StringBuilder details = new StringBuilder();
        int appointmentCounter = 1;
        int outcomeCounter = 1;

        // Search the Appointments.csv file
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 5 && values[0].equalsIgnoreCase(name)) {
                    if (!appointmentFound) {
                        details.append("Appointment details:\n");
                    }
                    details.append(appointmentCounter).append(")").append(line).append("\n");
                    appointmentFound = true;
                    appointmentCounter++;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the Appointments.csv file: " + e.getMessage());
        }

        // Search the AppointmentOutcomeRecord.csv file
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_OUTCOME_RECORD_CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 6 && values[0].equalsIgnoreCase(name)) {
                    if (!outcomeFound) {
                        details.append("\nAppointment outcome details:\n");
                    }
                    details.append(outcomeCounter).append(")").append(line).append("\n");
                    outcomeFound = true;
                    outcomeCounter++;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the AppointmentOutcomeRecord.csv file: " + e.getMessage());
        }

        if (!appointmentFound) {
            details.append("No current appointment details.\n");
        }
        if (!outcomeFound) {
            details.append("No current appointment outcome details.\n");
        }

        System.out.println(details.toString());

        

        
    }
}
