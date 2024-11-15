package Controller;
import View.LoginMenu;


public class AuthenticationController implements  Authentication{

    //attributes
    public LoginMenu view;

    //public constructor
    public AuthenticationController(LoginMenu view)
    {
        this.view=view;
    }
    //methods

    //@override the method
    public boolean toLogin(String userID, String userPass) {
        String filePass = CSVReader.getPassword("HMS/External Data/Users.csv", userID);
        if(userPass.equals(filePass)){
            return true;
        }
        else{
            return false;
        }

    }

}
