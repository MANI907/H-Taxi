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
- 적용 후 REST API 의 테스트
```




## 폴리글랏 퍼시스턴스

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>



## 폴리글랏 프로그래밍 - JAVA
