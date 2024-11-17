package Controller;
import java.util.*;
import Model.User;

public class PatientMenuHandler implements MenuHandler{
    private final AppointmentHandler appointmentHandler;

    public PatientMenuHandler(AppointmentHandler appointmentHandler){
        this.appointmentHandler = appointmentHandler;
    }
    @Override
    public void displayMenu() {
        System.out.println("Patient Menu");
        System.out.println("1. View Medical Record");
        System.out.println("2. Update personal information");
        System.out.println("3. Schedule an appointment");
        System.out.println("4. Reschedule an appointment");
        System.out.println("5. Cancel an appointment");
        System.out.println("6. View scheduled appointments");
        System.out.println("7. View past appointment outcome record");
        System.out.println("8. Change password");
        System.out.println("9. Log out");
        System.out.println();
    }

    @Override
    public void handleMenuOption(int choice, User user) {
        Scanner sc = new Scanner(System.in);
        switch(choice){

            case 3:
                appointmentHandler.scheduleAppointment(user);
                break;

            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
