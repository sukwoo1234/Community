# 익명 커뮤니티 프로그램

## 1. 개요

### 1.1 목적
본 프로그램은 인공지능 소프트웨어학과 학생들만 사용할 수 있는 익명 커뮤니티를 제공하기 위해 설계되었습니다. 이를 통해 학생들은 학업, 진로, 생활과 관련된 다양한 주제에 대해 자유롭고 익명으로 소통할 수 있습니다.

### 1.2 대상
이 프로그램은 인공지능 소프트웨어학과에 소속된 학생들을 주요 대상으로 하며, 학과 내 구성원만 이용할 수 있도록 설계되었습니다.

---

## 2. 프로그램의 중요성 및 필요성

### 2.1 중요성
- 학생들이 익명으로 소통할 수 있는 공간은 민감한 주제나 질문을 자유롭게 공유하는 데 중요한 역할을 합니다.
- 학과 내의 공감대와 유대감을 형성하고 강화하는 데 기여합니다.

### 2.2 필요성
- 학과 내의 커뮤니케이션과 협업 환경을 개선하기 위한 플랫폼이 부족합니다.
- 익명성과 폐쇄성을 보장하여 학업 및 개인적 고민을 자유롭게 논의할 수 있는 공간이 필요합니다.

---

## 3. 프로그램 수행 절차

### 3.1 다이어그램
- 아래는 프로그램의 전체 절차를 도식화한 순서도입니다:
  1. 사용자 로그인 (아이디, 비밀번호 확인)
  2. 게시글 작성 및 조회
  3. 댓글 추가 및 조회
  4. 데이터베이스 저장 및 관리
![github1](https://github.com/sukwoo1234/Community/blob/master/github1.png)


### 3.2 클래스 다이어그램
- 본 프로그램의 주요 클래스 다이어그램은 다음과 같습니다:
  - **Main**: 애플리케이션의 흐름을 제어하고 UI를 관리합니다.
  - ![github2](https://github.com/sukwoo1234/Community/blob/master/github2.png)
  - **Post**: 게시글 정보를 저장하고 관리합니다.
  - ![github3](https://github.com/sukwoo1234/Community/blob/master/github3.png)
  - **User**: 사용자 정보를 관리하고 인증 기능을 제공합니다.
  - ![github4](https://github.com/sukwoo1234/Community/blob/master/github4.png)
  - **Database**: 게시글의 데이터를 관리하고 댓글 기능을 포함합니다.
  - ![github5](https://github.com/sukwoo1234/Community/blob/master/github5.png)
  - **Comment**: 게시글에 달린 댓글을 나타내고 관리합니다.
  - ![github6](https://github.com/sukwoo1234/Community/blob/master/github6.png)

### 3.3 절차 설명
1. **로그인 및 인증**:
   - 사용자는 로그인 절차를 통해 시스템에 로그인합니다.
   - 익명성을 위해 사용자 이름은 데이터베이스에 저장되지 않습니다.

2. **게시글 및 댓글 작성**:
   - 사용자는 새로운 게시글을 작성하거나 기존 게시글을 조회할 수 있습니다.
   - 게시글에는 댓글을 추가할 수 있으며, 모든 활동은 익명으로 처리됩니다.

3. **데이터베이스 관리**:
   - 프로그램 종료 시, 게시글 및 댓글 정보는 데이터베이스에 저장됩니다.
   - 데이터베이스는 txt 형식으로 저장되며, 프로그램 재실행 시 로드됩니다.

---

## 4. 느낌점
본 프로그램을 개발하며 다음과 같은 점을 깨달았습니다:
- 객체 지향 설계의 중요성을 다시금 느꼈으며, 클래스 간의 관계를 체계적으로 설계하는 방법을 배웠습니다.
- 데이터베이스를 활용한 데이터 저장 및 로드 절차를 구현하며 실제 응용 프로그램 개발의 기초를 이해하게 되었습니다.
- 익명성을 유지하면서도 사용성을 높이는 설계가 사용자 중심의 소프트웨어 개발에서 얼마나 중요한지 배웠습니다.

---

## Contact
- Mail : tnrdn6712@naver.com
- GitHub : https://github.com/sukwoo1234
