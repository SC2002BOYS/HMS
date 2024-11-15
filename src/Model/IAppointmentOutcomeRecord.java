package Model;
import java.util.List;
import java.time.LocalDate;
import Type.ServiceType;
import Type.PrescriptionStatus;

public interface IAppointmentOutcomeRecord {
    LocalDate getDate();
    ServiceType getServiceProvided();
    List<String> getPrescriptions();
    String getConsultationNotes();
    List<PrescriptionStatus> getPrescriptionStatus();
}
