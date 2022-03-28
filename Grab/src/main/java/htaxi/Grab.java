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
    



}
