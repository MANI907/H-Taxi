package htaxi;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;


@Entity
@Table(name="AllocationManagement_table")
public class AllocationManagement  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    @PostPersist
    public void onPostPersist(){
        StartService startService = new StartService();
        BeanUtils.copyProperties(this, startService);
        startService.publishAfterCommit();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    



}
