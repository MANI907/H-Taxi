package htaxi;


public class StartService extends AbstractEvent {

    private Long id;
    private Integer allocationStatus;
    private String phoneNumber;
    private String startingPoint;
    private String destination;
    private String estimatedFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(Integer allocationStatus) {
        this.allocationStatus = allocationStatus;
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
    public String getEstimatedFee() {
        return estimatedFee;
    }

    public void setEstimatedFee(String estimatedFee) {
        this.estimatedFee = estimatedFee;
    }
}
