package Controller;
import View.LoginMenu;


public class AuthenticationController implements  Authentication{

    //attributes
    public final LoginMenu view = new LoginMenu();


    //@override the method
    public boolean toLogin(String userID, String userPass) {
        String filePass = CSVReader.getPassword("External Data/Users.csv", userID);
        if(userPass.equals(filePass)){
            return true;
        }
        else{
            return false;
        }

    }

}
