//package Controller;
//
//import View.ScheduleView;
//
//public class ScheduleManager {
//
//    private AppointmentManager appointmentManager;
//    private AvailabilityManager availabilityManager;
//    private ScheduleView scheduleView;
//
//    public ScheduleController(AppointmentManager appointmentManager, AvailabilityManager availabilityManager, ScheduleView scheduleView) {
//        this.appointmentManager = appointmentManager;
//        this.availabilityManager = availabilityManager;
//        this.scheduleView = scheduleView;
//    }
//
//    public void viewSchedule() {
//        scheduleView.viewUpcomingAppointments();
//        scheduleView.viewAvailableSlots();
//    }
//
//    public void manageAppointmentRequests() {
//        appointmentManager.manageAppointmentRequests();
//    }
//
//    public void setAvailability() {
//        availabilityManager.setAvailability();
//    }
//
//    public void updateAvailability() {
//        availabilityManager.updateAvailability();
//    }
//}
