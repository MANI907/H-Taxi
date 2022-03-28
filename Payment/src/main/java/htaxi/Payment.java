package htaxi;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;


@Entity
@Table(name="Payment_table")
public class Payment  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Integer paymentStatus;

    private String phoneNumber;

    private Long estimatedFee;


    @PostPersist
    public void onPostPersist(){
        PaymentApproved paymentApproved = new PaymentApproved();
        BeanUtils.copyProperties(this, paymentApproved);
        paymentApproved.publishAfterCommit();

    }

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
