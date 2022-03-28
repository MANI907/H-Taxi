package htaxi;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;


@Entity
@Table(name="Grab_table")
public class Grab  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Integer grabStatus;

    private String phoneNumber;

    private String startingPoint;

    private String destination;

    private Integer estimatedFee;


    @PostPersist
    public void onPostPersist(){
        GrabRequestConfirmed grabRequestConfirmed = new GrabRequestConfirmed();
        BeanUtils.copyProperties(this, grabRequestConfirmed);
        grabRequestConfirmed.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        htaxi.external.Payment payment = new htaxi.external.Payment();
        // mappings goes here
        GrabApplication.applicationContext.getBean(htaxi.external.PaymentService.class)
            .pay(payment);

        GrabCancelled grabCancelled = new GrabCancelled();
        BeanUtils.copyProperties(this, grabCancelled);
        grabCancelled.publishAfterCommit();

    }

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
