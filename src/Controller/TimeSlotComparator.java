package Controller;
import Model.AvailableSlot;
import java.util.Comparator;

public class TimeSlotComparator implements Comparator<AvailableSlot>{
    @Override
    public int compare(AvailableSlot a1, AvailableSlot a2) {
        return a1.getStartTime().compareTo(a2.getStartTime());
    }
}

