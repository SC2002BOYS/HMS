package Controller;

import Model.Patient;
import Model.User;
import Model.Schedule;
import View.ScheduleView;
import View.ViewPatientRecords;

import java.time.LocalDate;
import java.util.Scanner;

public class DoctorMenuHandler implements MenuHandler{
    private final ScheduleController scheduleController;
    private final AppointmentOutcomeRecordController appointmentOutcomeRecordController;


    public DoctorMenuHandler(Schedule schedule){
        this.scheduleController = new ScheduleController(schedule);
        this.appointmentOutcomeRecordController = new AppointmentOutcomeRecordController(schedule);
    }

    @Override
    public void displayMenu(){
        System.out.println("Doctor Menu");
        System.out.println("1. View patient medical records");
        System.out.println("2. Update patient medical records");
        System.out.println("3. View personal schedule");
        System.out.println("4. Set availability");
        System.out.println("5. Update availability");
        System.out.println("6. Accept or Decline appointment requests");
        System.out.println("7. View upcoming appointments");
        System.out.println("8. Record appointment outcome");
        System.out.println("9. Change Password");
        System.out.println("10:Log Out");
    }

    public void handleMenuOption(int choice, User doctor) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1:
                ViewPatientRecords viewPatientRecords = new ViewPatientRecords(doctor.getUserID());
//                System.out.print("Enter Patient ID: ");
//                String patientID = scanner.next();
//                Patient patient = new Patient(patientID, CSVReader.getPassword("External Data/Users.csv", patientID), CSVReader.getGender("External Data/Users.csv", patientID), CSVReader.getAge("External Data/Users.csv", patientID), new PatientMenuHandler(new PatientScheduleHandler()));
//                //prescriptionHandler.viewPatientRecords(patient);
                break;

            case 2:
//                System.out.print("Enter Patient ID: ");
//                String patientIDForDispense = scanner.next();
//
//                Patient patientForDispense = new Patient(patientIDForDispense, CSVReader.getPassword("External Data/AppointmentOutcomeRecord.csv",patientIDForDispense), CSVReader.getGender("External Data/Users.csv", patientIDForDispense), CSVReader.getAge("External Data/Users.csv", patientIDForDispense), new PatientMenuHandler(new PatientScheduleHandler()));
//
//                System.out.print("Enter Date (yyyy-MM-dd): ");
//                LocalDate date = LocalDate.parse(scanner.next());
                //prescriptionHandler.dispenseMedication(patientForDispense, date);
                break;

            case 3:
                scheduleController.viewSchedule();
                System.out.println();
                break;

            case 4:
                scheduleController.setAvailability();
                System.out.println();
                break;

            case 5:
                scheduleController.updateAvailability();
                System.out.println();
                break;

            case 6:
                scheduleController.manageAppointmentRequests();
                System.out.println();
                break;

            case 7:
                scheduleController.viewUpcomingAppointments();
                System.out.println();
                break;
            case 8:
                //Record Appt Outcome Error
                appointmentOutcomeRecordController.editAppointmentOutcomeRecord();
                System.out.println();
                break;
            case 9:
                System.out.print("Enter new password: ");
                String newPass = scanner.nextLine();
                PasswordController passChanger = new PasswordController(doctor.getUserID(), newPass);
                System.out.println();
                break;

            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
