package Controller;
import java.util.*;
import Model.Patient;
import java.time.LocalDate;
import Controller.CSVReader;
import Model.User;
import Type.Role;

public class PharmacistMenuHandler implements MenuHandler{
    private final PrescriptionHandler prescriptionHandler;

    public PharmacistMenuHandler(PrescriptionHandler prescriptionHandler) {
        this.prescriptionHandler = prescriptionHandler;
    }

    @Override
    public void displayMenu() {
        System.out.println("Pharmacist Menu");
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update prescription status");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit replenishment request");
        System.out.println("5. Change password");
        System.out.println("6. Logout");
        System.out.println();
    }

    public void handleMenuOption(int choice, User pharmacist) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1:
                System.out.print("Enter Patient ID: ");
                String patientID = scanner.next();
                Patient patient = new Patient(patientID, CSVReader.getPassword("External Data/Users.csv",patientID), Role.PATIENT); // Replace with actual retrieval logic
                prescriptionHandler.viewPatientRecords(patient);
                break;

            case 2:
                System.out.print("Enter Patient ID: ");
                String patientIDForDispense = scanner.next();
                Patient patientForDispense = new Patient(patientIDForDispense, CSVReader.getPassword("External Data/AppointmentOutcomeRecord.csv",patientIDForDispense), Role.PATIENT); // Replace with actual retrieval logic
                System.out.print("Enter Date (yyyy-MM-dd): ");
                LocalDate date = LocalDate.parse(scanner.next());
                prescriptionHandler.dispenseMedication(patientForDispense, date);
                break;

            case 4:
                System.out.print("Enter Today's Data(yyyy-MM-dd): ");
                LocalDate replenishmentDate = LocalDate.parse(scanner.next());
                System.out.print("Enter Medication in shortage: ");
                String meds = scanner.next();
                prescriptionHandler.requestPrescription(replenishmentDate, meds);
                break;



            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
