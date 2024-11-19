package Controller;

import Model.Appointment;
import Model.AvailableSlot;
import Model.Schedule;
import View.ScheduleView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DocAvailabilityManager {

    private Schedule schedule;
    private ScheduleView scheduleView;

    public DocAvailabilityManager(Schedule schedule){
        this.schedule = schedule;
        scheduleView = new ScheduleView(schedule);
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
        DocUpdateCSV.updateCSVWithModifiedAvailability(schedule);
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
                    DocUpdateCSV.updateCSVWithModifiedAvailability(schedule);
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
                DocUpdateCSV.updateCSVWithModifiedAvailability(schedule);
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
}
