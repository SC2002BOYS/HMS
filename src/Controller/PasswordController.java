package Controller;

import Model.User;

public class PasswordController implements UpdatePassword{

    //method
    @Override
    public void ChangePassword(String newPass, User model){
        model.setUserPass(newPass);
        System.out.println("Password updated successfully!");
    }
    
}
