package Controller;
import Model.User;

public interface MenuHandler {
    void displayMenu();
    void handleMenuOption(int choice, User user);
}
