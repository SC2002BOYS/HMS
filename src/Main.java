import java.util.*;

import Controller.*;
import Model.Doctor;
import Model.Patient;
import Model.Pharmacist;
import Model.User;
import Type.Role;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        AuthenticationController authController = new AuthenticationController();

        authController.view.printMenu();
        System.out.println("Enter your choice: ");
        int rolenum = sc.nextInt();

        Role role = null;
        switch (rolenum) {
            case 1:
                role = Role.PATIENT;
                break;
            case 2:
                role = Role.DOCTOR;
                break;
            case 3:
                role = Role.PHARMACIST;
                break;

            case 4:
                role = Role.ADMIN;
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        String userID = "";
        String userPass = "";

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Enter your userID: ");
            userID = sc.next();
            System.out.println("Enter your password: ");
            userPass = sc.next();

            if (authController.toLogin(userID, userPass)) {
                User user = new User(userID, userPass, role, CSVReader.getGender("External Data/Users.csv", userID), CSVReader.getAge("External Data/Users.csv", userID));
                System.out.println("Login Successful:" + " " + role + " " + user.getUserID());
                System.out.println();
                loggedIn = true;

            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        switch(role){

            case PHARMACIST:
                Pharmacist pharmacist = new Pharmacist(userID, CSVReader.getPassword("External Data/Users.csv", userID), CSVReader.getGender("External Data/Users.csv", userID), CSVReader.getAge("External Data/Users.csv", userID), new PharmacistMenuHandler(new PrescriptionServiceHandler()));
                pharmacist.runModule();
                break;

            case PATIENT:
                AppointmentHandler patientScheduler = new PatientScheduleHandler();
                MenuHandler patientMenuHandler = new PatientMenuHandler(patientScheduler);
                Patient patient = new Patient(userID, CSVReader.getPassword("External Data/Users.csv", userID), CSVReader.getGender("External Data/Users.csv", userID), CSVReader.getAge("External Data/Users.csv", userID), patientMenuHandler);
                patient.runModule();
                break;

            case DOCTOR:
                Doctor doctor = new Doctor(userID, CSVReader.getPassword("External Data/Users.csv", userID), CSVReader.getGender("External Data/Users.csv", userID), CSVReader.getAge("External Data/Users.csv", userID));
                doctor.runModule();
        }
    }
}