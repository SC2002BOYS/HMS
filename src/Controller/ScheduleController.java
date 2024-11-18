package Controller;

import Model.Schedule;
import Model.Appointment;
import Model.AvailableSlot;
import Type.AppointmentStatus;
import View.ScheduleView;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ScheduleController {

    private Schedule schedule;
    private ScheduleView scheduleView;
    private final String SCHEDULE_PATH = "External Data/Schedule.csv";
    private final String APPOINTMENT_PATH = "External Data/Appointments.csv";

    public ScheduleController(Schedule schedule){
        this.schedule= schedule;
        scheduleView = new ScheduleView(schedule);
    }

    public void viewSchedule(){
        scheduleView.viewUpcomingAppointments();
        System.out.println();
        scheduleView.viewAvailableSlots();
        System.out.println();
    }

    public void viewUpcomingAppointments(){
        scheduleView.viewUpcomingAppointments();
    }

    public void viewAppointmentRequests(){
        scheduleView.viewAppointmentRequests();
        System.out.println();
    }

    public void manageAppointmentRequests() {
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Appointment> appointmentRequests = schedule.getAppointmentRequests();
        Appointment current ;


        while (!validInput) {
            scheduleView.viewAppointmentRequests();
            System.out.println("Choose appointment request to manage:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                current = appointmentRequests.get(choice - 1);
                System.out.println("You chose: " + choice + "." + current.getStartTime().format(formatter) + " to " + current.getEndTime().format(formatter));
                System.out.println("Do you wish to accept? (Y/N)");
                String input = scanner.nextLine();
                input = input.toUpperCase();
                if (input.equals("Y")) {
                    removeAvailability(getAvailSlotIndex(current.getStartTime()));
                    updateCSVWithModifiedAvailability();
                    addAppointment(current);
                    updateCSVWithModifiedAppointment();
                } else if (input.equals("N")) {
                    current.setStatus(AppointmentStatus.CANCELLED);
                    updateCSVWithModifiedAppointment();
                    System.out.println("Appointment declined.");
                }
                appointmentRequests.remove(choice-1);
                validInput = true;

            } catch (Exception e) {
                System.out.println("Invalid format. Please try again.");
            }
        }
    }

    public void setAvailability(){
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        int SlotDurationMinutes;
        LocalDateTime startTime = null;
        LocalDateTime endTime;
        ArrayList<AvailableSlot> availableSlots = schedule.getAvailableSlots();
        ArrayList<Appointment> appointments = schedule.getAppointments();

        while(!validInput) {
            System.out.println("Enter start date and time (format: yyyy-MM-dd HH:mm):");
            String input = scanner.nextLine();
            try {
                startTime = LocalDateTime.parse(input, formatter);
                System.out.println("You entered: " + startTime.format(formatter));
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid format. Please use the correct format yyyy-MM-dd HH:mm");
                //scanner.nextLine();
            }

        }

        System.out.println("Enter slot duration in minutes:");
        SlotDurationMinutes = scanner.nextInt();
        endTime = startTime.plusMinutes(SlotDurationMinutes);

        for (AvailableSlot slot : availableSlots) {
            if (startTime.isBefore(slot.getEndTime()) && endTime.isAfter(slot.getStartTime())) {
                System.out.println("Slot overlaps with an available slot. Please try again.");
                return;
            }
        }
        for(Appointment appointment : appointments){
            if (startTime.isBefore(appointment.getEndTime()) && endTime.isAfter(appointment.getStartTime())) {
                System.out.println("Slot overlaps with an appointment. Please try again.");
                return;
            }
        }

        while(startTime.isBefore(endTime)){
            LocalDateTime nextSlot = startTime.plusMinutes(60);
            if(nextSlot.isAfter(endTime)){
                nextSlot = endTime;
            }
            AvailableSlot newSlot = new AvailableSlot(startTime, nextSlot);
            availableSlots.add(newSlot);
            startTime = nextSlot;
        }


        //UpdateCSV
        updateCSVWithModifiedAvailability();
        System.out.println("Appointment scheduled successfully!");
        System.out.println();
        System.out.println("Available slot added.");
    }

    public void updateAvailability(){
        boolean validInput = false;
        int slotDurationMinutes = -1;
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<AvailableSlot> availableSlots = schedule.getAvailableSlots();
        ArrayList<Appointment> appointments = schedule.getAppointments();
        int choice = -1;

        scheduleView.viewAvailableSlots();

        AvailableSlot currentSlot = null;
        while(!validInput) {
            System.out.println("Enter choice:");
            choice = scanner.nextInt();
            try {
                currentSlot = availableSlots.get(choice-1);
                System.out.println("You chose: " + choice + "." + currentSlot.getStartTime().format(formatter) + " to " + currentSlot.getEndTime().format(formatter));
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid format. Please try again");
                scanner.nextLine();
            }
        }

        validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter new slot duration in minutes:");
                slotDurationMinutes = scanner.nextInt();

                if(slotDurationMinutes == 0){
                    removeAvailability(choice-1);
                    updateCSVWithModifiedAvailability();
                    System.out.println("Available slot updated.");
                    return;
                }
                LocalDateTime startTime = currentSlot.getStartTime();
                LocalDateTime endTime = currentSlot.getStartTime().plusMinutes(slotDurationMinutes);

                //check if new slot overlaps with another available slot
                for (AvailableSlot current : availableSlots) {
                    if(!(startTime.isEqual(current.getStartTime()))) {
                        if (startTime.isBefore(current.getEndTime()) && endTime.isAfter(current.getStartTime())) {
                            System.out.println("Slot overlaps with another available slot. Please try again.");
                            return;
                        }
                    }
                }
                //check if new slot overlaps with an appointment.
                for(Appointment appointment : appointments){
                    if (startTime.isBefore(appointment.getEndTime()) && endTime.isAfter(appointment.getStartTime())) {
                        System.out.println("Slot overlaps with an appointment. Please try again.");
                        return;
                    }
                }

                if(slotDurationMinutes > 60){
                    startTime = startTime.plusMinutes(60);
                    currentSlot.setEndTime(startTime);
                    while(startTime.isBefore(endTime)){
                        LocalDateTime nextSlot = startTime.plusMinutes(60);
                        if(nextSlot.isAfter(endTime)){
                            nextSlot = endTime;
                        }
                        AvailableSlot newSlot = new AvailableSlot(startTime, nextSlot);
                        availableSlots.add(newSlot);
                        startTime = nextSlot;
                    }
                }
                else
                    currentSlot.setEndTime(endTime);
                updateCSVWithModifiedAvailability();
                System.out.println("Available slot updated.");
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input for slot duration. Please enter a valid number.");
                scanner.nextLine();
            }
        }

    }

    //Helper function
    private void removeAvailability(int index){
            schedule.getAvailableSlots().remove(index);
    }
    //Helper function
    private int getAvailSlotIndex(LocalDateTime startTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<AvailableSlot> availableSlots = schedule.getAvailableSlots();
        int index = 0;

        for (AvailableSlot current : availableSlots) {
            if((startTime.isEqual(current.getStartTime()))) {
                    return index;
                }
            index++;

            }
        System.out.println("Slot does not exist.");
        return -1;
    }

    //Helper function
    private void addAppointment(Appointment appointment){
        ArrayList<Appointment> appointments = schedule.getAppointments();
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointments.add(appointment);
        System.out.println("Appointment accepted");
    }

    private void updateCSVWithModifiedAvailability() {
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

    private void updateCSVWithModifiedAppointment() {
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



}
