package htaxi;


public class PaymentApproved extends AbstractEvent {

    private Long id;
    private Integer paymentStatus;
    private String phoneNumber;
    private Long estimatedFee;

    public PaymentApproved(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }
    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer PaymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Long getEstimatedFee() {
        return estimatedFee;
    }

    public void setEstimatedFee(Long EstimatedFee) {
        this.estimatedFee = estimatedFee;
    }
}
