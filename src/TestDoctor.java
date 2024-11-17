import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Model.MedicalRecord;
import Model.Appointment;
import Controller.ScheduleController;
import Type.Gender;
import View.ScheduleView;
import Model.Doctor;
import Type.Role;
import Type.AppointmentStatus;


public class TestDoctor {

    public static void main(String[] args) {

        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        Doctor doctor = new Doctor("xinyu", "password", Role.DOCTOR, Gender.MALE, "23", medicalRecords);

//Availability Test
        ScheduleView view = new ScheduleView(doctor.getSchedule());
        ScheduleController controller = new ScheduleController(doctor.getSchedule());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime startTimeTest = LocalDateTime.parse("2024-11-16 15:00", formatter);
        LocalDateTime endTimeTest = startTimeTest.plusMinutes(60);
        controller.addAppointment("John", startTimeTest,endTimeTest);

        startTimeTest = LocalDateTime.parse("2024-11-16 16:00", formatter);
        endTimeTest = startTimeTest.plusMinutes(60);
        controller.addAppointment("Mary", startTimeTest,endTimeTest);

        startTimeTest = LocalDateTime.parse("2024-11-16 17:00", formatter);
        endTimeTest = startTimeTest.plusMinutes(60);
        controller.addAppointment("Chloe", startTimeTest,endTimeTest);

//Appointment Request Test

        startTimeTest = LocalDateTime.parse("2024-11-16 00:00", formatter);
        endTimeTest = startTimeTest.plusMinutes(60);
        Appointment Req1 = new Appointment("Brandon", startTimeTest, endTimeTest, AppointmentStatus.PENDING);
        controller.addAppointmentRequest(Req1);

        startTimeTest = LocalDateTime.parse("2024-11-16 01:00", formatter);
        endTimeTest = startTimeTest.plusMinutes(60);
        Appointment Req2 = new Appointment("Brandon", startTimeTest, endTimeTest, AppointmentStatus.PENDING);
        controller.addAppointmentRequest(Req2);

        startTimeTest = LocalDateTime.parse("2024-11-16 02:00", formatter);
        endTimeTest = startTimeTest.plusMinutes(60);
        Appointment Req3 = new Appointment("Brandon", startTimeTest, endTimeTest, AppointmentStatus.PENDING);
        controller.addAppointmentRequest(Req3);



        while (choice != 0) {
            System.out.println("1. Set Avail");
            System.out.println("2. View Schedule");
            System.out.println("3. Update Avail");
            System.out.println("4. View appt reqs");
            System.out.println("5. Manage appt reqs");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("Input choice: ");
            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case (1):
                        controller.setAvailability();
                        break;
                    case (2):
                        controller.viewSchedule();
                        break;
                    case (3):
                        controller.updateAvailability();
                        break;
                    case(4):
                        controller.viewAppointmentRequests();
                        break;
                    case(5):
                        controller.manageAppointmentRequests();
                        break;
                    case(6):
                        controller.viewUpcomingAppointments();
                        break;
                }
            }catch(Exception e){
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }
}
