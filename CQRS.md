
## CQRS
설명

- 테이블 모델링(Grab)
|Column|Description|Type|
|------|---|---|---|------------------|
|id|배차ID|long|
|grabStatus|배차상태|Integer|
|phoneNumber|고객전화번호|String|
|startingPoint|출발지|String|
|destination|목적지|String|
|estimatedFee|예상금액|Integer|

- Entity Pattern 과 Repository Pattern 을 적용하여 JPA 를 통하여 다양한 데이터소스 유형 (RDB or NoSQL) 에 대한 별도의 처리가 없도록 데이터 접근 어댑터를 자동 생성하기 위하여 Spring Data REST 의 RestRepository 를 적용하였다. RDB로는 H2를 사용하였다.

```
package htaxi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestAllocationStatusRepository extends CrudRepository<RequestAllocationStatus, Long> {


}
```
