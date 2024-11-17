import Controller.AdministratorMenuHandler;
import Controller.AppointmentController;
import Controller.CSVReader;
import Controller.IAppointmentController;
import Controller.IReplenishmentController;
import Controller.IStaffController;
import Controller.ReplenishmentController;
import Controller.StaffController;
import Model.Administrator;
import View.HospitalStaffMenu;
import View.Menu;
import Type.Gender;

public class AdminTest {
    
    public static void main(String[] args) {
        System.out.println("This is a test for the Admin class");

         IStaffController staffController= new StaffController();
                IAppointmentController appointmentController = new AppointmentController();
                IReplenishmentController replenishmentController = new ReplenishmentController();
                Menu hospitalStaffMenu = new HospitalStaffMenu();
                AdministratorMenuHandler adminMenuHandler = new AdministratorMenuHandler(replenishmentController,appointmentController,staffController,hospitalStaffMenu);
                Administrator admin = new Administrator("shawn", "chow", Gender.MALE, "55", adminMenuHandler);
                admin.runModule();
    }
}
