public class User{

    private String userID;
    private String userPass;
    private Role role;

    public User(){
        this.userID = "";
        this.userPass = "";
        this.role = null;
    }

    public User(String userID, String userPass, Role role){
        this.userID = userID;
        this.userPass = userPass;
        this.role = role;
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
}
