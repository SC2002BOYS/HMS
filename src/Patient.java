import java.util.ArrayList;

public class Patient extends User {

    //attributes
    private MedicalRecord medicalRecord;
    private ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private ArrayList<Appointment> appointments;
    private PatientMenu menu;



    //constructor
    public Patient(String userID,String userpass, Role role, MedicalRecord medicalRecord, ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords, ArrayList<Appointment> appointments, PatientMenu menu){
        super(userID, userpass, role);
        this.medicalRecord = medicalRecord;
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.appointments = appointments;
        this.menu = menu;
    }

    //to print the patient menu
    public void printMenu(){
        this.menu.printMenu();
    }

    //getters and setters
    public MedicalRecord getMedicalRecord(){
        return this.medicalRecord;
    }  

    public void setMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecord = medicalRecord;
    }

    public ArrayList<AppointmentOutcomeRecord> getAppointmentOutcomeRecords(){
        return this.appointmentOutcomeRecords;
    }       

    public void setAppointmentOutcomeRecords(ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords){
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
    }

    public ArrayList<Appointment> getAppointments(){
        return this.appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments){
        this.appointments = appointments;
    }


}
