# 계층화 패턴 - Layered pattern

## 기본 설명

---

- 하위 모듈들의 그룹으로 나눌 수 있는 구조화된 프로그램에서 사용
- 각 하위 모듈들은 특정한 수준의 추상화를 제공
- n-티어 아키텍쳐 패턴이라고도 불림

### 계층

---

- 프레젠테이션 계층 (Presentation layer)
    - UI 계층 (UI layer)
- 어플리케이션 계층 (Application layer)
    - 서비스 계층 (Service layer)
- 비즈니스 논리 계층 (Business logic layer)
    - 도메인 계층 (Domain layer)
    - 4계층시 사용
- 데이터 접근 계층 (Data access layer)
    - 영속 계층 (Persistence layer)

### 도메인 기반 설계 (Domain Driven Design, DDD) 계층

---

- Presentation layer
- Application layer
- Domain layer
- Infra layer

### 계층 분리 이유

---

- 계층 없이 한 곳에서 작업을 할 경우
    - 코드의 복잡성 증가
    - 유지보수의 어려움
    - 중복 코드 증가
    - 확장성 감소

### 기본 개념

---

- 계층으로 되어있고 각 계층들끼리의 의존성을 가진다

![Untitled](/Untitled.png)

- layer 1은 하위 layer 2에 의존
- layer 2는 하위 layer 3에 의존
- layer 2와 연관있는 layer는 자기 자신, layer2, layer3이고 상위 layer나 다른 layer에는 관련이 없다

### Layerd Pattern 주의사항

---

- 상위 layer는 하위 레이어만 이용하도록 플로우의 방향성이 한가지여야한다
- 각 layer는 해당 layer가 의존하는 직접적인 하위 layer만 알면 되기 때문에 추상화 필요

### Presentation Layer

---

- 사용자 인터페이스 계층
- e.g. API 호출에 대한 Response

### Application Layer

---

- 비즈니스 로직을 정의하고 수행될 수 있도록 Domain과 infra layer를 연결
- 실질적인 데이터의 상태 변화는 domain layer로 위임
- 비즈니스 또는 도메인 관련 논리를 포함하지 않음

### Domain Layer

---

- 소프트웨어의 핵심
- 실질적인 도메인 정보를 포함하고 이를 책임지는 계층

### Infra Layer

---

- 가장 아래 계층
- 일반화된 기술을 제공하는 계층
- 외부 시스템을 호출하는 역할 담당

### 활용

---

- 일반적인 데스크톱 어플리케이션
- E-commerce 웹 어플리케이션
    
    ![Untitled](/Untitled1.png)