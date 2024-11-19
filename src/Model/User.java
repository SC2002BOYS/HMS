package Model;
import Type.Role;
import Type.Gender;

public class User{

    private String userID;
    private String userPass;
    private Role role;
    private Gender gender;
    private String age;

    public User(){
        this.userID = "";
        this.userPass = "";
        this.role = null;
    }

    public User(String userID, String userPass, Role role, Gender gender, String age){
        this.userID = userID;
        this.userPass = userPass;
        this.role = role;
        this.gender=gender;
        this.age=age;
           
    }

    public String getUserID(){
        return this.userID;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }

    public String getUserPass(){
        return this.userPass;
    }

    public void setUserPass(String userPass){
        this.userPass = userPass;
    }

    public Role getRole(){
        return this.role;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public void setGender(Gender gender)
    {
        this.gender=gender;
    }

    public Gender getGender(Gender gender)
    {
        return this.gender;
    }

    public void setAge(String age)
    {
        this.age=age;
    }

    public String getAge(String age)
    {
        return this.age;
    }

    public void runModule(){
        return;
    }
}
