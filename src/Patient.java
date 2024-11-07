import java.time.LocalDate;
import java.util.*;

public class Patient extends User{

    private String patientID;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private int contactInfo;
    private BloodType bloodType;
    private List<String> pastDiagnosis;

    public String getPatientID(){
        return this.patientID;
    }

    public void setPatientID(String patientID){
        this.patientID = patientID;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public LocalDate getDateOfBirth(){
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender(){
        return this.gender;
    }

    public void setGender(Gender gender){
        this.gender = gender;
    }

    public int getContactInfo(){
        return this.contactInfo;
    }

    public void setContactInfo(int contactInfo){
        this.contactInfo = contactInfo;
    }

    public BloodType getBloodType(){
        return this.bloodType;
    }

    public void setBloodType(BloodType bloodType){
        this.bloodType = bloodType;
    }

    public List<String> getPastDiagnosis(){
        return this.pastDiagnosis;
    }

    public void setPastDiagnosis(List<String> pastDiagnosis){
        this.pastDiagnosis = pastDiagnosis;
    }

    public Patient(String userID, String userPass, Role role, String patientID, String name, LocalDate dateOfBirth,
                   Gender gender, int contactInfo, BloodType bloodType, List<String> pastDiagnosis) {
        super(userID, userPass, role);  // Calls the User constructor
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.bloodType = bloodType;
        this.pastDiagnosis = pastDiagnosis;
    }
}
