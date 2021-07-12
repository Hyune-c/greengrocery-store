# 청과물 가게

## 1. 환경

- springboot 2.5.2
- thymeleaf

## 2. 주요 구현 기능 & 기술

> 구현된 기능은 스텝별로 Issue 와 PR 을 기록했습니다.

- webClient 를 활용한 외부 API 연동을 통한 조회
- accessToken 관리와 자동 갱신
- 표준 예외 처리
- MDC 를 활용한 로깅

## 3. 실행 방법

```shell
# 1. war 생성
> ./gradlew bootwar
BUILD SUCCESSFUL in 6s

> ls build/libs
greengrocery-store-0.0.1-SNAPSHOT.war

# 2. war 실행
> java -jar build/libs/greengrocery-store-0.0.1-SNAPSHOT.war
... Started GreengroceryStoreApplication in 4.513 seconds (JVM running for 5.048)

# 3. http://localhost:8080/index 접속
```

## 4. 동작 예시

### 시작 페이지

![img.png](src/main/resources/static/img.png)

### 청과물 종류를 선택하고 청과물을 선택합니다.

- 청과물 종류 선택에 따라 선택된 청과물의 이름이 있는 드랍다운 박스가 활성화 됩니다.
- 선택된 청과물 종류는 readOnly 입니다.
- 청과물 이름은 편의상 자동 입력되지만 수정 가능합니다.

![img_1.png](src/main/resources/static/img_1.png)

### 가격 조회 버튼을 클릭하면 청과물 이름과 가격이 조회됩니다.

![img_2.png](src/main/resources/static/img_2.png)
