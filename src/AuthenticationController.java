import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


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
    public boolean toLogin(String userID, String userPass)
    {

        String filename = "/Users/shawnchow/Desktop/JAVA/HMS/External Data/Users.csv"; //need full file path
        String line;
        String delimiter = ",";

        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            while((line=br.readLine()) != null)
            {

                String[] userCred = line.split(delimiter);
                String storedUserID = userCred[0];
                String storedUserPass = userCred[1];

                if(storedUserID.equals(userID) && storedUserPass.equals(userPass))
                {
                    return true;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
