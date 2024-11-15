package View;
import java.util.*;
import Model.AppointmentOutcomeRecord;
import Model.IAppointmentOutcomeRecord;


public class AllViewAppointmentOutcome {

    private static final AppointmentOutcomeRecordView view = new AppointmentOutcomeRecordView();

    public static void staffViewAppointmentOutcome(List<AppointmentOutcomeRecord> outcomeRecords){
        // Check if there are any outcome records and print them
        if (outcomeRecords != null && !outcomeRecords.isEmpty()) {
            for (IAppointmentOutcomeRecord record : outcomeRecords) {
                view.printAppointmentOutcomeRecord(record);
            }
        } else {
            System.out.println("No appointment outcome records found for patient");
        }    }
}