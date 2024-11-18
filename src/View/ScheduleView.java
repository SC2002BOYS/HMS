package View;

import Model.AvailableSlot;
import Model.Schedule;
import Model.Appointment;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ScheduleView {

    private Schedule schedule;

    public ScheduleView(Schedule schedule){
        this.schedule = schedule;
    }

    public void viewUpcomingAppointments(){
        System.out.println("Upcoming Appointments:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Appointment> appointments = schedule.getAppointments();
        int i = 1;
        for(Appointment appointment : appointments){
            System.out.println(i + ". " + appointment.getPatientID() + " , " + appointment.getStartTime().format(formatter) + " to " + appointment.getEndTime().format(formatter));
            i++;
        }
    }

    public void viewAvailableSlots(){
        System.out.println("Available Slots:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<AvailableSlot> availableSlots = schedule.getAvailableSlots();
        int i = 1;
        for(AvailableSlot slot : availableSlots){
            System.out.println(i + "." + slot.getStartTime().format(formatter) + " to " + slot.getEndTime().format(formatter));
            i++;
        }
    }

    public void viewAppointmentRequests(){
        System.out.println("Appointment Requests:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Appointment> appointmentRequests = schedule.getAppointmentRequests();
        int i = 1;
        for(Appointment appointmentRequest : appointmentRequests){
            System.out.println(i + ". " + appointmentRequest.getPatientID()+ " , " + appointmentRequest.getStartTime().format(formatter) + " to " + appointmentRequest.getEndTime().format(formatter) );
            i++;
        }

    }
}
