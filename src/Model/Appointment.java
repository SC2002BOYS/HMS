package Model;
import java.time.LocalDateTime;
import Type.AppointmentStatus;


public class Appointment {
    private String doctorID;
    private String patientID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AppointmentStatus status;

    public String getDoctorID(){
        return this.doctorID;
    }

    public void setDoctorID(String doctorID){
        this.doctorID = doctorID;
    }

    public String getPatientID(){
        return this.patientID;
    }

    public void setPatientID(String doctorID){
        this.doctorID = doctorID;
    }

    public LocalDateTime getStartTime(){
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime(){
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
    }

    public AppointmentStatus getStatus(){
        return this.status;
    }

    public void setStatus(AppointmentStatus status){
        this.status = status;
    }
    public Appointment(String patientID, String doctorID, LocalDateTime startTime, LocalDateTime endTime, AppointmentStatus status){
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

}
