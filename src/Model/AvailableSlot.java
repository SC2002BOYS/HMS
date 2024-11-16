package Model;

import java.time.LocalDateTime;

public class AvailableSlot {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public AvailableSlot(LocalDateTime startTime, LocalDateTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //Get start time
    public LocalDateTime getStartTime(){ return this.startTime; }

    //Get end time
    public LocalDateTime getEndTime(){ return this.endTime; }

    //Set start time
    public void setStartTime(LocalDateTime startTime){ this.startTime = startTime; }

    //Set endTime
    public void setEndTime(LocalDateTime endTime){ this.endTime = endTime; }
}
