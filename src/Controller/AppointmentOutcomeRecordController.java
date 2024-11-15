package Controller;
import java.time.LocalDate;
import java.util.*;
import Model.AppointmentOutcomeRecord;
import View.AppointmentOutcomeRecordView;
import Type.ServiceType;
import Type.PrescriptionStatus;

public class AppointmentOutcomeRecordController {
    
    //attributes
    private AppointmentOutcomeRecordView view;
    private AppointmentOutcomeRecord model;

    // Constructor
    public AppointmentOutcomeRecordController(AppointmentOutcomeRecordView view, AppointmentOutcomeRecord model) {
        this.view = view;
        this.model = model;
    }

    // View the appointment outcome record
    public void viewAppointmentOutcomeRecord() {
        view.printAppointmentOutcomeRecord(model);
    }

    // Methods to change the data in the model, will be used by doctor and pharmacist ==> will be in their attributes
    public void EditAppointmentOutcomeRecordDate(LocalDate date) {
        model.setDate(date);
    }

    public void EditAppointmentOutcomeRecordServiceProvided(ServiceType serviceProvided) {
        model.setServiceProvided(serviceProvided);
    }

    public void EditAppointmentOutcomeRecordPrescriptions(List<String> prescriptions) {
        model.setPrescription(prescriptions);
    }

    public void EditAppointmentOutcomeRecordPrescriptionStatus(List<PrescriptionStatus> prescriptionStatus) {
        model.setPrescriptionStatus(prescriptionStatus);
    }

    public void EditAppointmentOutcomeRecordConsultationNotes(String consultationNotes) {
        model.setConsultationNotes(consultationNotes);
    }


}
