public class PasswordController implements UpdatePassword{
    //object attribute
    private User model;

    //constructor
    public PasswordController(User model){
        this.model = model;
    }

    //method
    @Override
    public void ChangePassword(String newPass){
        model.setUserPass(newPass);
    }
}
