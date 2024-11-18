package Controller;
import java.util.*;

import Model.Patient;
import Model.User;
import View.ViewMedicalRecord;
import View.ViewPastApptOutcome;
import View.ViewPatientAppt;
import View.ViewPatientDetails;

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
        Patient patient = (Patient) user;
        switch(choice){

            case 1:
                System.out.println("Medical Record of " + user.getUserID());
                ViewMedicalRecord viewRecord = new ViewMedicalRecord();
                viewRecord.displayRecord(patient.getMedicalRecord());
                break;

            case 2:
                UpdateController updateController = new UpdateController();
                System.out.print("Enter new email: ");
                String newEmail = sc.nextLine();
                System.out.print("Enter new mobile number: ");
                int newNumber = sc.nextInt();
                IUpdate patientUpdate = new PatientUpdate(newEmail, newNumber);
                updateController.update(patientUpdate, patient.getMedicalRecord());
                break;

            case 3:
                appointmentHandler.scheduleAppointment(user);
                break;

            case 6:
                ViewPatientAppt viewPatientAppt = new ViewPatientAppt();
                viewPatientAppt.view(user.getUserID());
                break;

            case 7:
                ViewPastApptOutcome viewPast = new ViewPastApptOutcome();
                viewPast.view(user.getUserID());
                break;


            case 8:
                System.out.print("Enter new password: ");
                String newPass = sc.nextLine();
                PasswordController passChanger = new PasswordController(user.getUserID(), newPass);
                break;

            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
