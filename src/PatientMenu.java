public class PatientMenu implements Menu {

    @Override
    public void printMenu() {
        System.out.println("Patient Menu");
        System.out.println("1. View Medical Record");
        System.out.println("2. Update personal information");
        System.out.println("3. Schedule an appointment");
        System.out.println("4. Reschedule an appointment");
        System.out.println("5. Cancel an appointment");
        System.out.println("6. View scheduled appointments");
        System.out.println("7. View past appointment outcome record");
        System.out.println("8. Log out");
    }
    
}
