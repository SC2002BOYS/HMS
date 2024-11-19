package Controller;

import Model.Schedule;
import View.ScheduleView;

import javax.print.Doc;

public class ScheduleManager {

    private DocAppointmentManager appointmentManager;
    private DocAvailabilityManager availabilityManager;
    private ScheduleView scheduleView;

    public ScheduleManager(Schedule schedule) {
        this.appointmentManager = new DocAppointmentManager(schedule);
        this.availabilityManager = new DocAvailabilityManager(schedule);
        this.scheduleView = new ScheduleView(schedule);
    }

    public void viewSchedule() {
        scheduleView.viewUpcomingAppointments();
        scheduleView.viewAvailableSlots();
    }

    public void viewUpcomingAppointment(){
        appointmentManager.viewUpcomingAppointments();
    }

    public void manageAppointmentRequests() {
        appointmentManager.manageAppointmentRequests();
    }

    public void setAvailability() {
        availabilityManager.setAvailability();
    }

    public void updateAvailability() {
        availabilityManager.updateAvailability();
    }


}
