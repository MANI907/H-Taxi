
## CQRS(Command/Query/Responsibility/Segration)

- CQRS란?
  - 커맨드와 쿼리의 역할을 구분하기 위한 설계방법론
  
- CQRS의 적용
  -   데이터 원본을 매번 CRUD 할 필요 없이 취합된 별도의 뷰 미리 생성
  -   읽기전용 DB와 쓰기 DB를 분리함으로써 빠른 데이터 조회 구현
  -   Query 뷰를 다양하게 구성하여 여러 MSA 서비스 목적에 맞춰 각 서비스의 Polyglot Persistence 구현

- CQRS 적용의 장점
  -   읽기 행위 전용과 쓰기 전용의 데이터베이스를 2개 이상으로 나눌 수 있게 한다.
  -   도메인 이벤트를 저장하고 전 시스템의 전이상태를 보존하여 데이터베이스 시스템의 실패시에 데이터를 복구 할 수 있다.
  -   이벤트 스토어를 리플레이시켜서 어떤 데이터이든 상태를 재생성할 수 있다.

- 테이블 모델링(Grab)

|Column|Description|Type|
|------|---|---|
|id|배차ID|long|
|grabStatus|배차상태|Integer|
|phoneNumber|고객전화번호|String|
|startingPoint|출발지|String|
|destination|목적지|String|
|estimatedFee|예상금액|Integer|


- 커맨드/쿼리

|Event Store| ->Eventually | Read model DB|
|------------|--------|-------------|
|Data Access(Repositories)| |Read|
|Domain Model(Grab,Payment,Allocation)| |Model|
|Interface(UI/API) | |  |


<table>
  <tr>
    <td>Event Store</td>
    <td>->Eventually</td>
    <td>->Read model DB</td>
  </tr>
  <tr>
    <td colspan="2">내용</td>
  </tr>
</table>
