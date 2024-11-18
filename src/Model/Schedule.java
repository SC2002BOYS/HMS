package Model;

import Controller.CSVReader;

import java.util.ArrayList;


public class Schedule {

    private ArrayList<Appointment> appointments;
    private ArrayList<AvailableSlot> availableSlots;
    private ArrayList<Appointment> appointmentRequests;
    private String doctorID;
    private final String AVAIILABLE_SLOTS_PATH = "External Data/Schedule.csv";
    private final String APPOINTMENT_REQUESTS_PATH = "External Data/Appointments.csv";

    //Constructor
    public Schedule(String doctorID){

        //this.slotDurationMinutes = slotDurationMinutes;
        this.appointments = new ArrayList<Appointment>();
        this.availableSlots = CSVReader.getAvailableSlots(AVAIILABLE_SLOTS_PATH,doctorID);
        this.appointmentRequests = CSVReader.getAppointmentRequests(APPOINTMENT_REQUESTS_PATH,doctorID);
        this.doctorID = doctorID;
    }

    //Get appointments
    public ArrayList<Appointment> getAppointments() { return appointments; }

    //Get available slots
    public ArrayList<AvailableSlot> getAvailableSlots() { return availableSlots; }

    //Get appointment requests
    public ArrayList<Appointment> getAppointmentRequests(){ return this.appointmentRequests; }

    //Get doctorID;
    public String getDoctorID(){ return this.doctorID; }

}
