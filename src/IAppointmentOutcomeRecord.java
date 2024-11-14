import java.util.List;
import java.time.LocalDate;

public interface IAppointmentOutcomeRecord {
    LocalDate getDate();
    ServiceType getServiceProvided();
    List<String> getPrescriptions();
    String getConsultationNotes();
    List<PrescriptionStatus> getPrescriptionStatus();
}
