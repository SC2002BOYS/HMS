import java.time.LocalDateTime;

public class Appointment {
    private String doctorID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AppointmentStatus status;

    public String getDoctorID(){
        return this.doctorID;
    }

    public void setDoctorID(String doctorID){
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
    public Appointment(String doctorID, LocalDateTime startTime, LocalDateTime endTime, AppointmentStatus status){
        this.doctorID = doctorID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

}
