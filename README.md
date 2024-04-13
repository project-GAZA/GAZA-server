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
|  메인 페이지  |  응원 메시지 목록 페이지   |
| :-------------------------------------------: | :------------: |
|  <img width="500" src="https://github.com/project-GAZA/GAZA-server/assets/56196986/28f11e9d-d354-469f-8303-3d89b7731940"/> |  <img width="500" src="https://github.com/project-GAZA/GAZA-server/assets/56196986/6a7e6e5f-3c36-4dd3-ba6c-67a6ee7c051f">|  
| 응원메시지 카테고리 선택 페이지   |  응원 메시지 작성 페이지   |  
| <img width="500" src="https://github.com/project-GAZA/GAZA-server/assets/56196986/2ba63dfa-553e-46d7-87b7-1813ed74aba5"/>   |  <img width="500" src="https://github.com/project-GAZA/GAZA-server/assets/56196986/0750b7d6-03d5-42bd-a074-c2a45b9d12f2"/>     |
|  후원 페이지   |  
| <img width="500" src="https://github.com/project-GAZA/GAZA-server/assets/56196986/44176d77-21ea-4b69-ad82-750a4a864256"/>     |

---

## DB구조도
<img width="866" alt="image" src="https://github.com/project-GAZA/GAZA-server/assets/56196986/99048974-4315-4fd1-abf2-b8ee1ae6283f">

---

## System Architecture
![image](https://github.com/CafeCheckin/CafeCheckin/assets/56196986/9bca1262-770e-4f70-ac78-47b53a27997b)

---

## 기능정리
1. 메시지 작성
2. 동일한 IP당 하나의 메시지에 하나의 좋아요만 가능
3. 토스를 통한 후원 기능
4. 페이징 처리
