package Controller;
import Model.MedicalRecord;

public class PatientUpdate implements IUpdate{
    //patient calls the perform method to update the nonmedical info
    private String newEmail;
    private int newContactInfo;
    public PatientUpdate(String newEmail, int newContactInfo){
        this.newEmail = newEmail;
        this.newContactInfo = newContactInfo;
    }
    public void perform(MedicalRecord record){
        record.setContactInfo(newContactInfo);
        record.setEmail(newEmail);
    }
}