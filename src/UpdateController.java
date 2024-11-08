public class UpdateController{
    public void update(IUpdate update, MedicalRecord record){
        update.perform(record);
    }
}