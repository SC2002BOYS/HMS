public class Request {
    private String requesterID;
    private String medicationName;
    private int quantityRequested;
    private ApprovalStatus approvalStatus;

    public Request(String requestID, String medicationName, int quantityRequested, ApprovalStatus approvalStatus){
        this.requesterID = requestID;
        this.medicationName = medicationName;
        this.quantityRequested = quantityRequested;
        this.approvalStatus = approvalStatus;
    }

    public String getRequesterID(){
        return this.requesterID;
    }

    public void setRequesterID(String requesterID){
        this.requesterID = requesterID;
    }

    public String getMedicationName(){
        return this.medicationName;
    }

    public void setMedicationName(String medicationName){
        this.medicationName = medicationName;
    }

    public int getQuantityRequested(){
        return this.quantityRequested;
    }

    public void setQuantityRequested(int quantityRequested){
        this.quantityRequested = quantityRequested;
    }

    public ApprovalStatus getApprovalStatus(){
        return this.approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus){
        this.approvalStatus = approvalStatus;
    }
}
