public class LoginMenu implements Menu{

    @Override
    public void printMenu(){
        System.out.println("Welcome to the Hospital Management System");
        System.out.println("Login to access User Privileges");
        System.out.println("1. Patient Login");
        System.out.println("2. Doctor Login");
        System.out.println("3. Pharmacist Login");
        System.out.println("4. Admin Login");
    }
}
