package htaxi;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="RequestAllocationStatus_table")
public class RequestAllocationStatus {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

}