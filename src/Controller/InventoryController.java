package Controller;

import Model.AppointmentOutcomeRecord;

public class InventoryController {
    public void update(IUpdateInven update, AppointmentOutcomeRecord appointmentOutcomeRecord){
        update.perform(appointmentOutcomeRecord);
    }
}
