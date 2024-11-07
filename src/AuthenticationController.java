public class AuthenticationController implements  Authentication{

    //attributes
    private User model;
    private LoginMenu view;

    //public constructor
    public AuthenticationController(User model, LoginMenu view)
    {
        this.model=model;
        this.view=view;
    }
    //methods

    //@override the method
    public boolean toLogin(String userID, String userPass)
    {
        userID=model.getUserID();
        userPass=model.getUserPass();




    }

}
