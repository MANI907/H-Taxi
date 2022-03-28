package htaxi;


public class GrabCancelled extends AbstractEvent {

    private Long id;
    private Integer grabStatus;
    private String phoneNumber;
    private String startingPoint;
    private String destination;
    private Integer estimatedFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getGrabStatus() {
        return grabStatus;
    }

    public void setGrabStatus(Integer grabStatus) {
        this.grabStatus = grabStatus;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Integer getEstimatedFee() {
        return estimatedFee;
    }

    public void setEstimatedFee(Integer estimatedFee) {
        this.estimatedFee = estimatedFee;
    }
}
