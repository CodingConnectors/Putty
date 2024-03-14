# Putty

- 배포 URL : https://manjuu.shop:8443
- 테스트 ID : ttest@putty.com
- Password : putty123$

## 프로젝트 소개 
- 디자인은 애견샵 퍼피독을 참고하고
- 기능은 컴퓨존을 참고하여 만든 쇼핑몰입니다.

## 개발 기간
23.11.24 ~ 24.01.08

## 멤버구성
- 팀장 조영훈
- 부팀장 한재훈
- Time-Keeper 최현종
- 서기 김병욱

## 개발환경
- `java 17`
- `JDK 17.0.9`
- IDE : STS 4.20.0
- Framework : Springboot(2.7.17)
- spring : devtools, lombok, configuration-processor, mariaDB, JPA, thymeleaf, websocket(java17 이상), springsecurity, amqp, spring web
- security : springsecurity5
- Tamplate : thymeleaf
- Database : amazon RDS MariaDB 10.6.14
- webjars:stomp-websocket:2.3.4'
  webjars:sockjs-client:1.5.1'
  webjars:webjars-locator-core:0.55'
- aws:2.2.6.RELEASE

## 주요기능
#### 회원관련
- 로그인
- 회원가입
- 회원정보확인

#### 상품관련
- 상품등록
- 신상품, 베스트 상품표시
- 카테고리
- 상품 상세정보

#### 장바구니
- 장바구니 담기
- 바로결제, 선택결제
- 장바구니에서 물품 삭제

#### 결제
- 카드결제
- 카카오페이간편결제

#### 고객센터
- 문의

#### OpenAPI
- Daum 주소 API
- PortOne 결제 API

#### 관리자페이지
- 상품관리
- 헤더배너등록
