public class AuthenticationController implements  Authentication{

    //attributes
    private User user;
    private LoginMenu view;

    //public constructor
    public AuthenticationController(User model, LoginMenu view)
    {
        this.user=model;
        this.view=view;
    }
    //methods

    //@override the method
    public boolean toLogin(String userID, String userPass)
    {
        userID=user.getUserID();
        userPass=user.getUserPass();




    }

}
