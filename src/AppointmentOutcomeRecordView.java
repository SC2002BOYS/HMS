public class AppointmentOutcomeRecordView {
    
    // Methods
    public void printAppointmentOutcomeRecord(IAppointmentOutcomeRecord record) {
        System.out.println("Date: " + record.getDate());
        System.out.println("Service Provided: " + record.getServiceProvided());
        System.out.print("Prescriptions: ");
        for (String prescription : record.getPrescriptions()) {
            System.out.print(prescription + "[ " + record.getPrescriptionStatus() + " ]" + ", ");
        }
        System.out.println();
        System.out.println("Consultation Notes: " + record.getConsultationNotes());
    }


}
