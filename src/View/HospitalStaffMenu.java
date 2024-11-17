package View;

public class HospitalStaffMenu implements Menu{

    @Override
    public void printMenu() {
        System.out.println("Hospital Staff Menu");
        System.out.println("1. View list of hospital staff");
        System.out.println("2. Add a new staff member");
        System.out.println("3. Update a staff member");
        System.out.println("4. Remove a staff member");
        System.out.println("5. Back to administrator menu");
    }
    
}
