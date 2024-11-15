package View;
import java.util.*;
import Model.IAppointmentOutcomeRecord;
import Type.PrescriptionStatus;

public class AppointmentOutcomeRecordView {

    // Methods
    public void printAppointmentOutcomeRecord(IAppointmentOutcomeRecord record) {
        System.out.println("Date: " + record.getDate());
        System.out.println("Service Provided: " + record.getServiceProvided());
        System.out.print("Prescriptions: ");

        // Retrieve both prescriptions and statuses
        List<String> prescriptions = record.getPrescriptions();
        List<PrescriptionStatus> statuses = record.getPrescriptionStatus();

        // Ensure both lists are of the same length
        if (prescriptions.size() == statuses.size()) {
            for (int i = 0; i < prescriptions.size(); i++) {
                System.out.print(prescriptions.get(i) + "[" + statuses.get(i) + "]");

                // Print a comma and space if it's not the last item
                if (i < prescriptions.size() - 1) {
                    System.out.print(", ");
                }
            }
        } else {
            System.out.print("Error: Prescription and status counts do not match.");
        }

        System.out.println(); // New line after the prescriptions
        System.out.println("Consultation Notes: " + record.getConsultationNotes());
    }
}
