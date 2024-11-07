import java.util.*;

enum Role {
    PATIENT,
    DOCTOR,
    PHARMACIST,
    ADMIN
}

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        AuthenticationController authController = new AuthenticationController(new LoginMenu());

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

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Enter your userID: ");
            String userID = sc.next();
            System.out.println("Enter your password: ");
            String userPass = sc.next();

            if (authController.toLogin(userID, userPass)) {
                User user = new User(userID, userPass, role);
                System.out.println("Login Successful:" + " " + role + " " + user.getUserID());
                loggedIn = true;

            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
    }
}