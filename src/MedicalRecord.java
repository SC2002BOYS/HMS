import java.time.LocalDate;
import java.util.*;

public class MedicalRecord{

    private String patientID;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private int contactInfo;
    private BloodType bloodType;
    private List<String> pastDiagnosis;
    private String email;

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

    public String getEmail(){return this.email;};

    public void setEmail(String email){this.email= email;};

    public MedicalRecord(String patientID, String name, LocalDate dateOfBirth,
                   Gender gender, int contactInfo, BloodType bloodType, List<String> pastDiagnosis, String email) {
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.bloodType = bloodType;
        this.pastDiagnosis = pastDiagnosis;
        this.email = email;
    }
}
