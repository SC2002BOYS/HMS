package Controller;

import Model.Appointment;
import Model.AvailableSlot;
import Model.Schedule;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.time.*;

public class DocUpdateCSV {

    private static final String SCHEDULE_PATH = "External Data/Schedule.csv";
    private static final String APPOINTMENT_PATH = "External Data/Appointments.csv";

    public static void updateCSVWithModifiedAvailability(Schedule schedule) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<AvailableSlot> availableSlots = schedule.getAvailableSlots();

        // Step 1: Read all data from the CSV into memory
        ArrayList<String> updatedEntries = new ArrayList<>();
        ArrayList<String> allEntries;
        allEntries = CSVReader.getAllAvailSlotsEntries(SCHEDULE_PATH);

        // Step 2: Copy other doctor's available slots
        for(int i = 0; i<allEntries.size();i++){
            String[] values = allEntries.get(i).split(",");
            if(!(values[0].equals(schedule.getDoctorID()))) {
                updatedEntries.add(allEntries.get(i));
            }

        }

        //Sort the current Available Slot array
        Collections.sort(availableSlots, new TimeSlotComparator());

        // Step 3: Append current doctor's available slots

        for(int j = 0 ; j < availableSlots.size(); j++){
            String startTime = availableSlots.get(j).getStartTime().format(formatter);
            String doctorID = schedule.getDoctorID();

            // Create a new entry for the updated availability
            String newCsvEntry = doctorID + "," + startTime + "," + availableSlots.get(j).getEndTime().format(formatter);

            updatedEntries.add(newCsvEntry);
        }

        // Step 4: Write the updated list back to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCHEDULE_PATH))) {
            for (String entry : updatedEntries) {
                writer.write(entry); // Write each updated line back to the file
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public static void updateCSVWithModifiedAppointment(Schedule schedule) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Appointment> appointmentRequests = schedule.getAppointmentRequests();

        // Step 1: Read all data from the CSV into memory
        ArrayList<String> updatedEntries = new ArrayList<>();
        ArrayList<String> allEntries;
        allEntries = CSVReader.getAllAppointmentsEntries(APPOINTMENT_PATH);

        // Step 2: Copy entries that are not current doctor's appointment requests
        for(int i = 0; i<allEntries.size();i++){
            String[] values = allEntries.get(i).split(",");
            if(!(values[4].equals("PENDING")) || !(values[1].equals(schedule.getDoctorID()))) {
                updatedEntries.add(allEntries.get(i));
            }

        }

        for(int j = 0 ; j < appointmentRequests.size(); j++){
            String startTime = appointmentRequests.get(j).getStartTime().format(formatter);
            String doctorID = appointmentRequests.get(j).getDoctorID();
            String patientID = appointmentRequests.get(j).getPatientID();

            // Create a new entry for the updated availability
            String newCsvEntry = patientID + "," + doctorID + "," + startTime + "," + appointmentRequests.get(j).getEndTime().format(formatter) + "," + appointmentRequests.get(j).getStatus();

            updatedEntries.add(newCsvEntry);
        }

        // Step 4: Write the updated list back to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_PATH))) {
            for (String entry : updatedEntries) {
                writer.write(entry); // Write each updated line back to the file
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public static void updateScheduleInCSV(String doctorID, LocalDateTime newStart, LocalDateTime newEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        File originalFile = new File(SCHEDULE_PATH);
        StringBuilder updatedContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");

                // Append all lines unchanged
                updatedContent.append(line).append(System.lineSeparator());
            }

            // Append the new appointment to the content
            updatedContent.append(String.format("%s,%s,%s%n",
                    doctorID,
                    newStart.format(formatter),
                    newEnd.format(formatter)));

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Write the updated content back to the same file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(originalFile, false))) {
            writer.write(updatedContent.toString());
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

}
