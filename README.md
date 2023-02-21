# 실시간 쿠폰 발급 API 백엔드

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

현재는 DB에 Exclusive Lock(배타적 잠금)을 걸어 동시 요청에 대한 데이터 정확성과 무결성을 지키면서 해결하였습니다.

하지만 막대한 트래픽을 감당할 수 있을지는 잘 모르겠습니다. 

DB만 이용했을 때 감당하기 힘들다는 것을 가정한다면 캐시를 이용하는 방법도 찾아봐야겠습니다.

### 1-3. 기술 스택

- Langauge : `Java 11`
- Framework : `Spring Boot 2.7.8`
- Database : `MySQL 8.0`, `JPA`, `QueryDSL`
- API Documentation : `Swagger 3.0.0`

<br>

## 2. 개발 내용
### 2-1. 백엔드 아키텍처
<img width="798" alt="image" src="https://user-images.githubusercontent.com/80039556/220278524-9dbd7c71-4d01-4fc1-b07e-c44cc7a45718.png">

### 2-2. 데이터베이스 ERD
<img width="854" alt="image" src="https://user-images.githubusercontent.com/80039556/220288462-8f463061-52a3-4a58-af00-04a98fddb5be.png">
- COUPON : 쿠폰 정보
- MEMBER : 회원 정보
- MEMBER_COUPON : 회원이 발급받은 쿠폰 내역

### 2-3 API 문서화
<img width="1141" alt="image" src="https://user-images.githubusercontent.com/80039556/220289029-3f706454-526f-4929-adcf-3091563ef8b1.png">

### 2-4 부하 테스트

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

