# 선착순 쿠폰 발급 API 백엔드

## 1. 프로젝트 개요
### 1-1. 프로젝트 소개
- 선착순 쿠폰 발급을 위한 백엔드 시스템
- 육각형 아키텍처(Hexagonal Architecture) 스타일
- 요구사항
  - 선착순으로 쿠폰 발급
  - 중복 발급 X
  - 멀티 서버라고 가정
<img width="400" alt="image" src="https://user-images.githubusercontent.com/80039556/220270455-eb158309-37ed-44f0-92c6-83c94e121dab.png">

### 1-2. 문제 해결 전략

- 쿠폰 발급 이벤트는 **특정 시간(짧은 시간)** 에 **대용량 트래픽** 발생
- **수많은 동시 요청** 에서 제한적인 쿠폰수량과 선착순 발급에 대한 **정확성과 무결성** 을 지켜야 함

DB에 Exclusive Lock(배타적 잠금)을 걸어 동시요청에 대한 데이터 정확성고 무결성을 지키면서 해결하였음.

하지만 막대한 트래픽이 왔을 때 해당 쿠폰 DB에 잠금을 걸었기에 쿠폰을 사용하는 다른 서비스도 대기를 하거나 데드락이 걸릴 가능성이 있습니다.

따라서 redis를 이용한 Distribution Lock(분산락)을 사용하게 되었습니다.

분산락으로 하나의 공유자원(쿠폰)에 접근할 때 데이터 결함이 발생하지 않도록 원자성(atomic)을 보장하여 여러 서버에서의 동시성 이슈를 해결하였습니다.

### 1-3. 기술 스택

- Language : `Java 11`
- Framework : `Spring Boot 2.7.8`
- Database : `MySQL 8.0`, `JPA`, `QueryDSL`, `Redis`
- API Documentation : `Swagger 3.0.0`

<br>

## 2. 개발 내용
### 2-1. 백엔드 아키텍처
<img width="798" alt="image" src="https://user-images.githubusercontent.com/80039556/220278524-9dbd7c71-4d01-4fc1-b07e-c44cc7a45718.png">

### 2-2. 육각형 아키텍처 패키지 구조
- 회원쿠폰 발급 API에 관한 클래스만 적은 패키지 구조
<img width="665" alt="image" src="https://user-images.githubusercontent.com/80039556/220345959-70a49133-d86a-4a1f-bd6e-bc75440394dc.png">

<img width="731" alt="image" src="https://user-images.githubusercontent.com/80039556/220345884-c3beb04d-f929-4831-9981-e696c26206b5.png">

<img width="1100" alt="image" src="https://user-images.githubusercontent.com/80039556/220341229-e8e13aac-0d12-43df-afcd-abc837712e6e.png">

<img width="656" alt="image" src="https://user-images.githubusercontent.com/80039556/220346288-a175bf58-fae0-4a34-95aa-78c9e0e0cc4a.png">


### 2-3. 데이터베이스 ERD
<img width="854" alt="image" src="https://user-images.githubusercontent.com/80039556/220288462-8f463061-52a3-4a58-af00-04a98fddb5be.png">
- COUPON : 쿠폰 정보
- MEMBER : 회원 정보
- MEMBER_COUPON : 회원이 발급받은 쿠폰 내역

### 2-4 API 문서화
<img width="1141" alt="image" src="https://user-images.githubusercontent.com/80039556/220289029-3f706454-526f-4929-adcf-3091563ef8b1.png">

### 2-5 부하 테스트

<img width="247" alt="image" src="https://user-images.githubusercontent.com/80039556/220295853-4f4a2d18-d61f-4c53-9a70-98ff4ec6ca0e.png">

- JMeter를 사용하여 5초안에 2000명 요청하는 것을 5번 반복
- 쿠폰남은개수는 1000개이고, 회원은 10000명이 있음
- MEMBER_COUPON 값 : 뒤죽박죽 요청된 것을 볼 수 있음
<img width="686" alt="image" src="https://user-images.githubusercontent.com/80039556/220296440-e975c9a1-b666-4864-8f84-1fd8091e23a5.png">

- COUPON 값 : 제한된 1000개의 수량만큼만 빠진것을 볼 수 있음
<img width="683" alt="image" src="https://user-images.githubusercontent.com/80039556/220296640-51a3453f-56f7-43b1-b133-63c02d5abf10.png">

- 발급된 회원쿠폰 개수 : 1000개만 발급된 것을 볼 수 있음
<img width="484" alt="image" src="https://user-images.githubusercontent.com/80039556/220296750-f5547c97-c4a1-4de2-bef1-efb66e67c479.png">

- 회원쿠폰 테이블에서 중복 발급되었는지 조회 : 중복 발급이 없는 것을 볼 수 있음
  - 11번 쿠폰에 대해서만 발급요청을 했기에 회원id(mem_id)가 중복됐는지만 체크하면 됨
<img width="269" alt="image" src="https://user-images.githubusercontent.com/80039556/220330483-123f1a72-4ae4-42dc-8210-08205191e6b1.png">

### 2-6 쿠폰발급 테스트
<img width="1023" alt="image" src="https://github.com/Sangyong-Jeon/coupon_fifo-concurrency_issue/assets/80039556/e0c56eaf-11be-4532-905c-194acfda7c50">

- 15개 쓰레드 생성하여 10000명의 회원쿠폰 발급을 병렬처리 테스트함
