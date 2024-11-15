package Model;
import java.util.*;
import java.time.LocalDate;
import Type.ServiceType;
import Type.PrescriptionStatus;

public class AppointmentOutcomeRecord implements IAppointmentOutcomeRecord {
    
    // Attributes 
    private LocalDate date;
    private ServiceType serviceProvided;  // Use enum for service type
    private List<String> prescriptions;   // List to hold multiple prescriptions
    private List<PrescriptionStatus> prescriptionStatus; // List to hold status of each prescription
    private String consultationNotes;
    
    // Constructor
    public AppointmentOutcomeRecord(LocalDate date, ServiceType serviceProvided, List<String> prescriptions,List<PrescriptionStatus> prescriptionStatus ,String consultationNotes) {
        this.date = date;
        this.serviceProvided = serviceProvided;
        this.prescriptions = prescriptions;
        this.consultationNotes = consultationNotes;
        this.prescriptionStatus = prescriptionStatus;
    }
    
    // Getters and Setters
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public ServiceType getServiceProvided() {
        return serviceProvided;
    }

    public void setServiceProvided(ServiceType serviceProvided) {
        this.serviceProvided = serviceProvided;
    }

    @Override
    public List<String> getPrescriptions() {
        return new ArrayList<>(prescriptions); // Return a copy for encapsulation
    }

    public void setPrescription(List<String> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    @Override
    public List<PrescriptionStatus> getPrescriptionStatus() {
        return new ArrayList<>(prescriptionStatus); // Return a copy for encapsulation
    }

    public void setPrescriptionStatus(List<PrescriptionStatus> statuses) {
        this.prescriptionStatus = statuses;
    }
}
