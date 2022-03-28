# 구현:

서비스를 로컬에서 실행하는 방법은 아래와 같다 
각 서비스별로 bat 파일로 실행한다. 

cd app
mvn spring-boot:run

cd pay
mvn spring-boot:run 

cd store
mvn spring-boot:run  

cd customer
python policy-handler.py 


## DDD 의 적용
각 서비스내에 도출된 핵심 Aggregate Root 객체를 Entity 로 선언하였다: (예시는 pay 마이크로 서비스). 이때 가능한 현업에서 사용하는 언어 (유비쿼터스 랭귀지)를 그대로 사용하려고 노력했다. 하지만, 일부 구현에 있어서 영문이 아닌 경우는 실행이 불가능한 경우가 있기 때문에 계속 사용할 방법은 아닌것 같다. (Maven pom.xml, Kafka의 topic id, FeignClient 의 서비스 id 등은 한글로 식별자를 사용하는 경우 오류가 발생하는 것을 확인하였다

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




Entity Pattern 과 Repository Pattern 을 적용하여 JPA 를 통하여 다양한 데이터소스 유형 (RDB or NoSQL) 에 대한 별도의 처리가 없도록 데이터 접근 어댑터를 자동 생성하기 위하여 Spring Data REST 의 RestRepository 를 적용하였다
