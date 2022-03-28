package htaxi;


public class PaymentApproved extends AbstractEvent {

    private Long id;
    private Integer paymentStatus;
    private String phoneNumber;
    private Long estimatedFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Long getEstimatedFee() {
        return estimatedFee;
    }

    public void setEstimatedFee(Long estimatedFee) {
        this.estimatedFee = estimatedFee;
    }
}
