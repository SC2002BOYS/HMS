package Controller;

import Model.Schedule;
import Model.Appointment;
import Model.AvailableSlot;
import Type.AppointmentStatus;
import View.ScheduleView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleController {

    private Schedule schedule;
    private ScheduleView scheduleView;

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
        Appointment current = null;

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
                    addAppointment(current);
                } else if (input.equals("N")) {
                    current.setStatus(AppointmentStatus.DECLINED);
                    System.out.println("Appointment declined.");
                }
                appointmentRequests.remove(choice-1);
                return;

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

        AvailableSlot newSlot = new AvailableSlot(startTime, endTime);
        availableSlots.add(newSlot);
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
                currentSlot.setEndTime(endTime);
                System.out.println("Available slot updated.");
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input for slot duration. Please enter a valid number.");
                scanner.nextLine();
            }
        }

    }

    private void removeAvailability(int index){
        schedule.getAvailableSlots().remove(index);
    }

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



    public void addAppointment(String userID, LocalDateTime startTime, LocalDateTime endTime){
        ArrayList<Appointment> appointments = schedule.getAppointments();
        appointments.add(new Appointment(userID, startTime, endTime, AppointmentStatus.ACCEPTED));
        System.out.println("Appointment accepted");
    }

    public void addAppointment(Appointment appointment){
        ArrayList<Appointment> appointments = schedule.getAppointments();
        appointment.setStatus(AppointmentStatus.ACCEPTED);
        appointments.add(appointment);
        System.out.println("Appointment accepted");
    }

    public void addAppointmentRequest(Appointment appointment){
        schedule.getAppointmentRequests().add(appointment);
    }


}
