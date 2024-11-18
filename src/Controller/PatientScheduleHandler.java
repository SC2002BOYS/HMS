package Controller;

import Model.User;
import Type.AppointmentStatus;
import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class PatientScheduleHandler implements AppointmentHandler{
    private final String APPOINTMENT_PATH = "External Data/Appointments.csv";
    private final String STAFF_PATH = "External Data/Staff_List.csv";
    private final String SCHEDULE_PATH = "External Data/Schedule.csv";

    @Override
    public void scheduleAppointment(User user){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Scanner sc = new Scanner(System.in);
        //Select a doctor
        System.out.println("List of Doctors");
        System.out.println();
        showDoctors();
        System.out.print("Enter Doctor Name from above list: ");
        String doctor = sc.nextLine();

        if (!isDoctorValid(doctor)) {
            System.out.println("Invalid doctor name. Please try again.");
            return;
        }

        //Select time slot
        System.out.println("Choose a timeslot");
        System.out.println();
        showAvailableSlots(doctor);
        System.out.print("Enter slot start date and time (format: yyyy-MM-dd HH:mm): ");
        String input1 = sc.nextLine().trim();
        LocalDateTime start = LocalDateTime.parse(input1,formatter);
        LocalDateTime end = start.plusMinutes(60);

        if (!isTimeSlotValid(doctor, start, end)) {
            System.out.println("Invalid time slot. Please select a valid slot from the available options.");
            return;
        }

        //Add request to appointment csv
        if(!hasAppointmentConflict(user.getUserID(), start)){
            writeTOCSV(user.getUserID(),doctor, start, end);
            System.out.println("Appointment scheduled successfully!");
            System.out.println();
        }
        else{
            System.out.println("There is an appointment conflict!");
        }
    }

    @Override
    public void rescheduleAppointment(User user){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Scanner sc = new Scanner(System.in);
        //Check user's booked schedule
        showUserSlots(user);
        System.out.print("Pick the timeslot to change (format: yyyy-MM-dd HH:mm): ");
        String input = sc.nextLine().trim();
        LocalDateTime startInitial = LocalDateTime.parse(input, formatter);

        
        try(BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_PATH))) {
            String line;
            String doctorID = null;
            // Find the appointment to reschedule
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 4 && user.getUserID().equals(details[0]) &&
                        LocalDateTime.parse(details[2].trim(), formatter).equals(startInitial)) {
                    doctorID = details[1];
                    break;
                }
            }

            if(doctorID == null){
                System.out.println("No matching appointment found to reschedule.");
                return;
            }

            // Select time slot
            System.out.println("Choose a new timeslot");
            System.out.println();
            showAvailableSlots(doctorID);
            System.out.print("Enter slot start date and time (format: yyyy-MM-dd HH:mm): ");
            String input1 = sc.nextLine().trim();
            LocalDateTime newStart = LocalDateTime.parse(input1,formatter);
            System.out.print("Enter slot end date and time (format: yyyy-MM-dd HH:mm): ");
            String input2 = sc.nextLine().trim();
            LocalDateTime newEnd = LocalDateTime.parse(input2,formatter);
            
            if (!isTimeSlotValid(doctorID, newStart, newEnd)) {
                System.out.println("Invalid time slot. Please select a valid slot from the available options.");
                return;
            }
            
            // Update the CSV with the new details
            if(!hasAppointmentConflict(user.getUserID(), newStart)){
                updateAppointmentInCSV(user.getUserID(), doctorID, startInitial, newStart, newEnd);
                System.out.println("Appointment rescheduled successfully!");
            }
            else{
                System.out.println("There is an appointment conflict!");
            }            
        }
        catch(IOException e){
            System.err.println("Error reading appointments: " + e.getMessage());            
        }
        catch(Exception e){
            System.err.println("Error processing schedule data: " + e.getMessage());            
        }        
    }

    @Override
    public void cancelAppointment(User user){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Scanner sc = new Scanner(System.in);
        //Check user's booked schedule
        showUserSlots(user);
        System.out.print("Pick the timeslot to change (format: yyyy-MM-dd HH:mm): ");
        String input = sc.nextLine().trim();
        LocalDateTime startInitial = LocalDateTime.parse(input, formatter);

        try(BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_PATH))) {
            String line;
            String doctorID = null;
            // Find the appointment to reschedule
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 4 && user.getUserID().equals(details[0]) &&
                        LocalDateTime.parse(details[2].trim(), formatter).equals(startInitial)) {
                    doctorID = details[1];
                    break;
                }
            }

            if(doctorID == null){
                System.out.println("No matching appointment found to cancel.");
                return;
            }

            removeAppointmentInCSV(user.getUserID(), doctorID, startInitial);
            System.out.println("Appointment cancelled successfully!");
        }
        catch(IOException e){
            System.err.println("Error reading schedules: " + e.getMessage());           
        }
        catch(Exception e){
            System.err.println("Error processing schedule data: " + e.getMessage());            
        }        
    }

    private boolean hasAppointmentConflict(String userID, LocalDateTime newStart){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try(BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_PATH))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] details = line.split(",");
                String username = details[0].trim();
                if(details.length >= 4 && userID.equalsIgnoreCase(username)){
                    LocalDateTime start = LocalDateTime.parse(details[2].trim(), formatter);
                    if(start.equals(newStart)){
                        return true;
                    }
                }
            }
        }
        catch(IOException e){
            System.err.println("Error reading schedules: " + e.getMessage());
        }
        catch(Exception e){
            System.err.println("Error reading schedules: " + e.getMessage());
        }
        return false;
    }

    private void showUserSlots(User user){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try(BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_PATH))) {
            String line;
            System.out.println("Booked slots for " + user.getUserID() + ":");
            System.out.println("-------------------------------");
            while((line = reader.readLine()) != null){
                String[] details = line.split(",");
                if(details.length >= 4 && user.getUserID().equalsIgnoreCase(details[0].trim())){
                    LocalDateTime start = LocalDateTime.parse(details[2].trim(), formatter);
                    LocalDateTime end = LocalDateTime.parse(details[3].trim(), formatter);
                    System.out.printf("\n");
                    System.out.printf("Date: %s%n", start.toLocalDate());
                    System.out.printf("Time Slot: %s to %s%n", start.toLocalTime(), end.toLocalTime());
                    System.out.println("--------------------------------");                    
                }
            }
        }
        catch(IOException e){
            System.err.println("Error reading schedules: " + e.getMessage());           
        }
        catch(Exception e){
            System.err.println("Error processing schedule data: " + e.getMessage());            
        }
    }

    private void removeAppointmentInCSV(String userID, String doctorID, LocalDateTime oldStart){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<String> updatedLines = new ArrayList<>();
    
        // Step 1: Read all lines from the CSV and remove the old entry
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                
                if (details.length >= 4 && userID.equals(details[0]) && 
                    doctorID.equals(details[1]) &&
                    LocalDateTime.parse(details[2].trim(), formatter).equals(oldStart)) {
                    // Skip the old appointment entry
                    continue;
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading appointments file: " + e.getMessage());
        }

        // Write the updated lines back to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_PATH))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to appointments file: " + e.getMessage());
        }      
    }

    private void updateAppointmentInCSV(String userID, String doctorID, LocalDateTime oldStart, LocalDateTime newStart, LocalDateTime newEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<String> updatedLines = new ArrayList<>();
    
        // Step 1: Read all lines from the CSV and remove the old entry
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                
                if (details.length >= 4 && userID.equals(details[0]) && 
                    doctorID.equals(details[1]) &&
                    LocalDateTime.parse(details[2].trim(), formatter).equals(oldStart)) {
                    // Skip the old appointment entry
                    continue;
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading appointments file: " + e.getMessage());
        }
    
        // Step 2: Add the new appointment entry
        String newEntry = userID + "," + doctorID + "," + newStart.format(formatter) + "," + newEnd.format(formatter) + "," + AppointmentStatus.PENDING;
        updatedLines.add(newEntry);
    
        // Step 3: Write all lines back to the CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_PATH))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to appointments file: " + e.getMessage());
        }
    }
    

    private void showDoctors(){
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_PATH))) {
            String line;
            System.out.println("List of Doctors:");
            System.out.println("----------------");
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 2 && "DOCTOR".equalsIgnoreCase(details[1].trim())) {
                    System.out.println("Doctor " + details[0]);
                    System.out.println("----------------");
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading staff list: " + e.getMessage());
        }
    }

    private void showAvailableSlots(String doctorID){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (BufferedReader reader = new BufferedReader(new FileReader(SCHEDULE_PATH))) {
            String line;
            System.out.println("Available Time Slots for Doctor: " + doctorID);
            System.out.println("--------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(doctorID)) {
                    LocalDateTime start = LocalDateTime.parse(details[1].trim(), formatter);
                    LocalDateTime end = LocalDateTime.parse(details[2].trim(), formatter);

                    // Loop through the time slots
                    while (start.isBefore(end)) {
                        LocalDateTime nextSlot = start.plusMinutes(60);
                        if (nextSlot.isAfter(end)) {
                            nextSlot = end;
                        }
                        System.out.printf("Date: %s%n", start.toLocalDate());
                        System.out.printf("Time Slot: %s to %s%n", start.toLocalTime(), nextSlot.toLocalTime());
                        System.out.println("--------------------------------");
                        start = nextSlot;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading schedules: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing schedule data: " + e.getMessage());
        }
    }

    private void writeTOCSV(String userID,String doctorID, LocalDateTime start, LocalDateTime end){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_PATH, true))) {
            // Start with writing the new entry instead of appending
            String csvEntry = userID + "," + doctorID + "," + start.format(formatter) + "," + end.format(formatter) + "," + AppointmentStatus.PENDING;
            writer.write(csvEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to appointments file: " + e.getMessage());
        }
    }

    private boolean isTimeSlotValid(String doctorID, LocalDateTime start, LocalDateTime end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (BufferedReader reader = new BufferedReader(new FileReader(SCHEDULE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(doctorID)) {
                    LocalDateTime availableStart = LocalDateTime.parse(details[1].trim(), formatter);
                    LocalDateTime availableEnd = LocalDateTime.parse(details[2].trim(), formatter);

                    // Check if the requested slot fits into the available slot
                    while (availableStart.isBefore(availableEnd)) {
                        LocalDateTime nextSlot = availableStart.plusMinutes(60);
                        if (nextSlot.isAfter(availableEnd)) {
                            nextSlot = availableEnd;
                        }
                        if (start.equals(availableStart) && end.equals(nextSlot)) {
                            return true;
                        }
                        availableStart = nextSlot;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading schedules: " + e.getMessage());
        }
        return false;
    }

    private boolean isDoctorValid(String doctorID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(doctorID) && "DOCTOR".equalsIgnoreCase(details[1].trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading staff list: " + e.getMessage());
        }
        return false;
    }
}
