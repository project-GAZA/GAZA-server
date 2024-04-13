# 가자지구 주민들에게 희망메시지 전달 서비스 README

## 팀원 구성
<div align="center">

<div align="center">

| **이준석** |
| :------: | 
| [<img src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/422a81d3-b0b7-4b85-af31-a42a3c23c771" height=150 width=150> <br/> @JunRock](https://github.com/JunRock) |

</div>
</div>
<br>

## 개발 환경
- BACKEND : Spring Framework, Java11, Mysql, Spring Data Jpa, NCP, Docker, Docker-compose, Query dsl, CI/CD
- 버전 및 이슈관리 : Github, Github actions
- 서비스 배포 환경 : Spring Boot Apache Tomcat  <br>

## 주제
- 이스라엘과 팔레스타인의 전쟁으로 피해를 입은 가자지구 주민들에게 응원메시지를 전달해주는 서비스
  
## 요구사항
- 사용자는 익명으로 메시지를 작성할 수 있어야 한다.
- 메시지 목록을 최신순 혹은 인기순으로 정렬하여 볼 수 있어야한다.
- 사용자는 동일한 IP로 메시지 당 한번씩 좋아요를 누를 수 있어야한다.
- 사용자는 메시지를 남기면서 동시에 토스를 이용하여 후원을 헐 수 있어야한다.

## 참고사항 및 조건
- 사용자는 동일한 IP로 하나의 메시지에 무한 좋아요 누를 수 있는 것을 제한햐여야한다.
- 사용자가 토스 후원 중 중간에 문제가 생기면 후원 관련 작업을 모두 Rollback 시켜야한다.
- 사용자가 메시지만 남겼는지 혹은 후원도 같이 했는지 구분을 할 줄 알아야한다.

## 화면 구성 📺
|  메인 페이지  |  메인 페이지   |
| :-------------------------------------------: | :------------: |
|  <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/a55006f1-d3e8-4d27-9d0d-31718141dbb8"/> |  <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/10ebee86-ecd9-4dae-88d2-05eaebba2bea">|  
| 게시글 목록 페이지   |  게사판 댓글 작성 페이지   |  
| <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/93514b68-1ec9-4ef9-80f2-78e9e22ef1a0"/>   |  <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/9e4bce96-a695-4cc7-9ccc-708a860b3bec"/>     |
| 댓글 상세보기 페이지   |  카페 상세 정보 페이지   |  
| <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/33f1fec1-d92a-4176-bfe0-214835b7da47"/>   |  <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/94710406-e69f-4c5e-88bc-ddc161c0ccda"/>     |

---

## DB구조도
![image](https://github.com/CafeCheckin/CafeCheckin/assets/56196986/688eb59c-713d-4443-8d08-d1aea8bcbc78)

---

## System Architecture
![image](https://github.com/CafeCheckin/CafeCheckin/assets/56196986/9bca1262-770e-4f70-ac78-47b53a27997b)

---

## 기능정리
1. 유저 등록, 조회 수정, 삭제(탈퇴)
2. 카페 리뷰 등록, 조회, 수정, 삭제, 좋아요표시
3. 게시글 등록, 조회, 수정, 삭제, 좋아요 표시
4. 페이징 처리
5. Session Auth
