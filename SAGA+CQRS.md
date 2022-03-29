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


## 서비스 호출 흐름
- 고객이 목적지를 설정하고 택시배차를 요청한다.

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

- 고객이 결제한다.
- 결제가 완료되면 요청내용(승차장소, 목적지)이 택시기사에게 전달된다.
- 택시기사는 수신된 배차정보를 승인하고 승차장소로 이동한다.


