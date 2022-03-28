H Taxi
============
카카오택시 따라잡기
---------------
<img src = "https://t1.kakaocdn.net/kakaomobility/company_website/contents/v2/10-taxi-sub-4.jpg" width = "700">

# Table of contents
- [예제 - 택시호출](#---)
  - [서비스 시나리오](#서비스-시나리오)
  - [체크포인트](#체크포인트)
  - [분석/설계](#분석설계)
  - [구현:](#구현-)
    - [DDD 의 적용](#ddd-의-적용)
    - [폴리글랏 퍼시스턴스](#폴리글랏-퍼시스턴스)
    - [폴리글랏 프로그래밍](#폴리글랏-프로그래밍)
    - [동기식 호출 과 Fallback 처리](#동기식-호출-과-Fallback-처리)
    - [비동기식 호출 과 Eventual Consistency](#비동기식-호출-과-Eventual-Consistency)
  - [운영](#운영)
    - [CI/CD 설정](#cicd설정)
    - [동기식 호출 / 서킷 브레이킹 / 장애격리](#동기식-호출-서킷-브레이킹-장애격리)
    - [오토스케일 아웃](#오토스케일-아웃)
    - [무정지 재배포](#무정지-재배포)

# 서비스 시나리오
- 기능적 요구사항
    + 고객이 목적지를 설정하고 택시배차를 요청한다.
    + 고객이 결제한다.
    + 결제가 완료되면 요청내용(승차장소, 목적지)이 택시기사에게 전달된다.
    + 택시기사는 수신된 배차정보를 승인하고 승차장소로 이동한다.
    + 고객이 배차요청을 취소할 수 있다.
    + 배차요청이 취소(결제취소)가 되면 배차도 취소된다.
    + 택시기사는 배차요청/승인 상태를 중간중간 조회한다.
    + 배차상태가 바뀌면 카톡을 알림을 보낸다.
- 비기능적 요구사항
    + 트랜잭션
    + 장애격리


# 체크포인트

1. Saga
1. CQRS
1. Correlation
1. Req/Resp
1. Gateway
1. Deploy/ Pipeline
1. Circuit Breaker
1. Autoscale (HPA)
1. Zero-downtime deploy (Readiness Probe)
1. Config Map/ Persistence Volume
1. Polyglot
1. Self-healing (Liveness Probe)


# 분석/설계

## AS-IS 조직 (Horizontally-Aligned)
  ![image](https://github.com/MANI907/H-Taxi/blob/main/Images/%EB%B6%84%EC%84%9D%EC%84%A4%EA%B3%84_AS-IS.png?raw=true)

## TO-BE 조직 (Vertically-Aligned)
  ![조직구조](https://github.com/MANI907/H-Taxi/blob/main/Images/%EB%B6%84%EC%84%9D%EC%84%A4%EA%B3%84_TO_BE.jpg?raw=true)


## Event Storming 결과
* MSAEz 로 모델링한 이벤트스토밍 결과:  https://labs.msaez.io/#/storming/kNx981SCgIRu8zSkCyuoC6n2vzN2/dadf2150564b567c84b8f55dcf9500fa

