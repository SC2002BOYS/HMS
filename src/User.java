public class User{
    private String userID;
    private String userPass;
    private String role;

    public User(){
        this.userID = "";
        this.userPass = "";
        this.role = "";
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

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }
}
