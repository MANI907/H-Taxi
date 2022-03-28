package htaxi;


public class StartService extends AbstractEvent {

    private Long id;
    private Integer allocationStatus;
    private String phoneNumber;
    private String startingPoint;
    private String destination;
    private String estimatedFee;

    public StartService(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }
    public Integer getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(Integer AllocationStatus) {
        this.allocationStatus = allocationStatus;
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
    public String getEstimatedFee() {
        return estimatedFee;
    }

    public void setEstimatedFee(String EstimatedFee) {
        this.estimatedFee = estimatedFee;
    }
}
