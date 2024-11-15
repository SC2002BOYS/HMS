package View;

public class DoctorMenu implements Menu{

    @Override
    public void printMenu() {
        System.out.println("Doctor Menu");
        System.out.println("1. View medical record");
        System.out.println("2. Update medical record");
        System.out.println("3. View personal schedule");
        System.out.println("4. Set availability");
        System.out.println("5. Update availability");
        System.out.println("5. Accept or Decline appointment requests");
        System.out.println("6. View upcoming appointments");
        System.out.println("7. View past appointment outcome record");
        System.out.println("8. Log out");
    }
}
