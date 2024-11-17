package Controller;

import Model.User;
import java.time.*;

public interface AppointmentHandler{
    void scheduleAppointment(User user);
    void rescheduleAppointment(User user);
    void cancelAppointment(User user);
}
