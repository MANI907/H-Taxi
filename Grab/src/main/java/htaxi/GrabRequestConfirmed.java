package htaxi;


public class GrabRequestConfirmed extends AbstractEvent {

    private Long id;
    private Integer grabStatus;
    private String phoneNumber;
    private String startingPoint;
    private String destination;
    private Long estimatedFee;

    public GrabRequestConfirmed(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }
    public Integer getGrabStatus() {
        return grabStatus;
    }

    public void setGrabStatus(Integer GrabStatus) {
        this.grabStatus = grabStatus;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String StartingPoint) {
        this.startingPoint = startingPoint;
    }
    public String getDestination() {
        return destination;
    }

    public void setDestination(String Destination) {
        this.destination = destination;
    }
    public Long getEstimatedFee() {
        return estimatedFee;
    }

    public void setEstimatedFee(Long EstimatedFee) {
        this.estimatedFee = estimatedFee;
    }
}
