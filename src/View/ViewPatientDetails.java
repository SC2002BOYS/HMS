package View;

public abstract class ViewPatientDetails {
    protected String userID;
    public ViewPatientDetails(String userID){
        this.userID = userID;
    }
    public abstract void view();
}
