# ✈️Traveler
<p align="center"><img src="https://github.com/H4R1B0/final_PJT/assets/12294460/5d79cd86-f3ee-4ecf-9018-7359b6152cca" width="300px"></p>

## 🏝Traveler Web Page
> REST API + Vue를 사용한 관광지 조회 사이트</br>SSAFY 9기 1학기에서 배운 내용을 통해 만들어본 프로젝트

## ⏱개발 기간
- 23.05.09 ~ 23.05.25 (17일)
## 👫멤버 소개
- 팀장: 박현준
  - 프론트엔드 - 메인화면, 관광지 조회, 로그인, 회원가입, 관광지 페이징, 마이페이지(좋아요, 개인 정보 수정)
  - 백엔드 - 관광지 조회, 로그인, 회원가입, 마이페이지(좋아요, 개인 정보 수정)
- 팀원: 이은성
  - 프론트엔드 - 전체적인CSS, 공지사항, 후기 게시판, 게시판 페이징, 마이페이지(비밀번호 변경)
  - 백엔드 - 관광지 좋아요 반영, 공지사항, 후기 게시판, 게시판 페이징, 마이페이지(비밀번호 변경)

## 😀시작 가이드
### 요구사항
- Node.js 18.16.0
- Npm 9.5.1
- java 8
- springboot 2.x
- mysql 8
### 설치 방법
```
$ git clone https://github.com/H4R1B0/final_PJT.git
$ cd final_PJT
```
#### 프론트엔드
```
$ cd traveler-front
$ npm install
$ npm run serve
```

## 📚STACKS
### Environment
<img src="https://img.shields.io/badge/visualstudiocode-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white"> <img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

### Config
<img src="https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white">

### Development
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/vuedotjs-4FC08D?style=for-the-badge&logo=vuedotjs&logoColor=white"> <img src="https://img.shields.io/badge/nodedotjs-339933?style=for-the-badge&logo=nodedotjs&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

### Communication
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/discord-5865F2?style=for-the-badge&logo=discord&logoColor=white">

## 📌주요 기능
### 🌟회원가입 및 로그인
- salt와 해시를 이용한 비밀번호 암호화
- jwt를 통해 사용자 인증
### 🌟관광지 검색
- 검색 종류(제목, 주소)와 키워드에 따라 검색
- 키워드 자동완성
- 로그인한 경우 관광지 관심❤️ 추가 및 제거
- 카카오 API를 통해 지도 표시 및 출발지로부터 거리 계산
### 🌟공지사항 및 후기 게시판
- jwt를 통해 게시글 등록, 수정 및 삭제 권한 확인
