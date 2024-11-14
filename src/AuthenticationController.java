import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


public class AuthenticationController implements  Authentication{

    //attributes
    protected LoginMenu view;

    //public constructor
    public AuthenticationController(LoginMenu view)
    {
        this.view=view;
    }
    //methods

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
