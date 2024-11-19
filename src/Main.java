import java.util.*;
import Controller.*;
import Model.User;
import Type.Role;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        AuthenticationController authController = new AuthenticationController();
        AuthenticationHandler authHandler = new AuthenticationHandler();

        authController.view.printMenu();
        System.out.println("Enter your choice: ");
        int rolenum = sc.nextInt();
        Role[] allRoles = Role.values();
        Role role = allRoles[rolenum-1];
        User user = authHandler.authenticate(authController, role);
        user.runModule();
    }
}