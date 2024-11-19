package Controller;

import Model.Appointment;
import Model.AvailableSlot;
import Model.Schedule;
import Type.AppointmentStatus;
import View.ScheduleView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DocAppointmentManager {

    Schedule schedule;
    ScheduleView scheduleView;

    public DocAppointmentManager(Schedule schedule){
        this.schedule = schedule;
        scheduleView = new ScheduleView(schedule);
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
        if (appointmentRequests.isEmpty()){
            System.out.println("No available requests :) ");
            return;
        }

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
                    DocUpdateCSV.updateCSVWithModifiedAvailability(schedule);
                    addAppointment(current);
                    DocUpdateCSV.updateCSVWithModifiedAppointment(schedule);
                } else if (input.equals("N")) {
                    current.setStatus(AppointmentStatus.CANCELLED);
                    DocUpdateCSV.updateCSVWithModifiedAppointment(schedule);
                    System.out.println("Appointment declined.");
                }
                appointmentRequests.remove(choice-1);
                validInput = true;

            } catch (Exception e) {
                System.out.println("Invalid format. Please try again.");
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

    private void addAppointment(Appointment appointment){
        ArrayList<Appointment> appointments = schedule.getAppointments();
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointments.add(appointment);
        System.out.println("Appointment accepted");
    }

}
