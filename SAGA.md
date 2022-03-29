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
3개의 도메인으로 관리되고 있으며 배차요청(Grab), 결제(Payment), 배차할당(Allocation)으로 구성된다.

```
@Document
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
    	
    	//배차요청
        GrabRequestConfirmed grabRequestConfirmed = new GrabRequestConfirmed();
        
        BeanUtils.copyProperties(this, grabRequestConfirmed);
        grabRequestConfirmed.publishAfterCommit();

        htaxi.external.Payment payment = new htaxi.external.Payment();
        payment.setId(getid());

        GrabApplication.applicationContext.getBean(htaxi.external.PaymentService.class)
            .pay(payment);

        GrabCancelled grabCancelled = new GrabCancelled();
        BeanUtils.copyProperties(this, grabCancelled);
        grabCancelled.publishAfterCommit();

    }
    
    
    
package htaxi;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="grabs", path="grabs")
public interface GrabRepository extends PagingAndSortingRepository<Grab, Long>{


}


```



## 폴리글랏 퍼시스턴스

Grab/pom.xml

```
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

```


## 서비스 호출 흐름(동기식 호출)
배차요청(grab) -> 결제(pay) 간의 호출은 동기식 일관성을 유지하는 트랜잭션으로 처리
호풀 프로토콜은 REST 서비스를 FeignClient 를 이용하여 호출하도록 한다.

- 고객이 목적지를 설정하고 택시배차를 요청한다.

- 결제서비스를 호출하기 위하여 Stub과 (FeignClient) 를 이용하여 Service 대행 인터페이스 (Proxy) 를 구현
- 
```
# (Grab) PaymentService.java

package htaxi.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="Payment", url="http://localhost:8080")
public interface PaymentService {
    @RequestMapping(method= RequestMethod.GET, path="/payments")
    public void pay(@RequestBody Payment payment);

}

```

- 배차요청을 받은 직후(@PostPersist) 결제를 요청하도록 처리

```
@PostPersist
public void onPostPersist(){

	//배차요청
	GrabRequestConfirmed grabRequestConfirmed = new GrabRequestConfirmed();

	BeanUtils.copyProperties(this, grabRequestConfirmed);
	grabRequestConfirmed.publishAfterCommit();

	htaxi.external.Payment payment = new htaxi.external.Payment();
	payment.setId(getid());

	GrabApplication.applicationContext.getBean(htaxi.external.PaymentService.class)
	    .pay(payment);

	GrabCancelled grabCancelled = new GrabCancelled();
	BeanUtils.copyProperties(this, grabCancelled);
	grabCancelled.publishAfterCommit();

}

```

## 서비스 호출 흐름(비동기식 호출)

- 결제가 완료가 되면 배차할당 배차요청내용(승차장소, 목적지, 고객 전화번호) 택시기사에게 전달하는 행위는 동기식이 아니라 비동기식으로 처리해 배차할당 상태 변경이 블로킹 되지 않도록 처리

- 이를 위해 결제에 기록을 남긴 직후 결제승인이 됐다는 도메인 이벤트 카프카로 송출
```
package htaxi;

@Entity
@Table(name="Payment_table")
public class Payment {

 ...
    @PrePersist
    public void onPrePersist(){
     	PaymentApproved paymentApproved = new PaymentApproved();
        BeanUtils.copyProperties(this, paymentApproved);
        paymentApproved.publishAfterCommit();
    }

}

```
- 배차할당관리에서는 결제승인 이벤트를 수신해 PolicyHandler에서 정책 처리
- 택시기사는 수신된 배차정보를 승인하고 승차장소로 이동한다.
- 위치 : Allocation/src/main/java/htaxi/PolicyHandler.java

```
package htaxi;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_ConfirmAllocation(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### 배차할당 받음 : " + paymentApproved.toJson() + "\n\n");
  
  }
```

