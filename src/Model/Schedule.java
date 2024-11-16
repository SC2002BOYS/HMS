package Model;

import java.util.ArrayList;


public class Schedule {

    private ArrayList<Appointment> appointments;
    private ArrayList<AvailableSlot> availableSlots;
    private ArrayList<Appointment> appointmentRequests;

    //Constructor
    public Schedule(){

        //this.slotDurationMinutes = slotDurationMinutes;
        this.appointments = new ArrayList<Appointment>();
        this.availableSlots = new ArrayList<AvailableSlot>();
        this.appointmentRequests = new ArrayList<Appointment>();
    }

    //Get appointments
    public ArrayList<Appointment> getAppointments() { return appointments; }

    //Get available slots
    public ArrayList<AvailableSlot> getAvailableSlots() { return availableSlots; }

    //Get appointment requests
    public ArrayList<Appointment> getAppointmentRequests(){ return this.appointmentRequests; }


}
