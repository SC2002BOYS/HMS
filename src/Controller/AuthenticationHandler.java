package Controller;
import Model.*;
import Type.Role;
import View.HospitalStaffMenu;
import java.util.Scanner;

public class AuthenticationHandler {

    public User authenticate(Authentication authenticator, Role role){
        boolean loggedIn = false;
        boolean length = false;
        Scanner sc = new Scanner(System.in);
        String userID = "";

        while (!loggedIn){
            System.out.print("Enter your userID: ");
            userID = sc.next();
            System.out.print("Enter your password: ");
            String userPass = sc.next();

            if(authenticator.toLogin(userID, userPass)){
                User user = new User(userID, userPass, role, CSVReader.getGender("External Data/Users.csv", userID), CSVReader.getAge("External Data/Users.csv", userID));
                System.out.println("Login Successful:" + " " + role + " " + user.getUserID());
                System.out.println();
                if(userPass.equals("password123")) {
                    System.out.println("Initial login! Please set a strong password");
                    String newPW = null;
                    while (!length) {
                        System.out.print("Enter your new password: ");
                        newPW = sc.next();
                        if (newPW.length() <= 11) {
                            System.out.println("Password length too short, enter stronger password");
                        } else {
                            length = true;
                        }
                    }

                    PasswordController changePW = new PasswordController(userID, newPW);

                }
                loggedIn = true;

            }
            else {
                System.out.println("Invalid username or password. Please try again.");
            }


        }
        return switch (role) {
            case PHARMACIST ->
                    new Pharmacist(userID, CSVReader.getPassword("External Data/Users.csv", userID), CSVReader.getGender("External Data/Users.csv", userID), CSVReader.getAge("External Data/Users.csv", userID), new PharmacistMenuHandler(new PrescriptionServiceHandler()));
            case PATIENT ->
                    new Patient(userID, CSVReader.getPassword("External Data/Users.csv", userID), CSVReader.getGender("External Data/Users.csv", userID), CSVReader.getAge("External Data/Users.csv", userID), new PatientMenuHandler(new PatientScheduleHandler()));
            case DOCTOR ->
                    new Doctor(userID, CSVReader.getPassword("External Data/Users.csv", userID), CSVReader.getGender("External Data/Users.csv", userID), CSVReader.getAge("External Data/Users.csv", userID));
            case ADMIN ->
                    new Administrator(userID, CSVReader.getPassword("External Data/Users.csv", userID), CSVReader.getGender("External Data/Users.csv", userID), CSVReader.getAge("External Data/Users.csv", userID), new AdministratorMenuHandler(new HospitalStaffMenu()));
        };
    }
}
