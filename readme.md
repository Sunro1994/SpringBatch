# SpringBatch

작성일시: 2024년 6월 3일 오후 9:03
복습: No

## 목차

## SpringBatch

- Spring Batch는 대용량 데이터 배치 처리에 관한 프레임워크이다.
- 이는 데이터 집계, 변환 및 이관 작업과 같은 반복적인 대규모 처리 작업을 지원한다.

## 주요 개념

- Job
    - 하나 이상의 step으로 구성된 배치 처리 작업의 단위
    - Job은 실행될 전체 작업의 청사진이다.
- Step
    - 실제 작업을 수행하는 단위
    - 각step은 Tsaklet또는 ItemReader, ItemProcessor, ItemWriter로 구성될 수 있다.
- Tasklet
    - Step내에서 단일 작업을 수행하는 인터페이스
    - 간단한 작업에 적합하며, execute메서드 하나로 정의된다.
- ItemReader, ItemProcessor, ItemWriter
    - SpringBatch는 데이터 읽기, 처리 및 쓰기를 담당하는 이 세가지 구성 요소로 작업을 정의할 수 있다.
    - ItemReader는 데이터를 읽고, ItemProcessor는 데이터를 처리하며, ItemWriter는 데이터를 쓴다.
- JobLauncher
    - JobLauncher는 Job을 실행하는데 사용된다.
    - Job과 JobParameters를 받아서 Job을 실행한다.
- JobBuilderFactory와 StepBuilderFactory
    - Job과 Step을 생성하는데 사용되는 팩토리 클래스
- JobParameters
    - JobParameters는 Job실행 시 필요한 매개변수를 나타낸다.
    - 동일한 Job을 서로 다른 매개변수로 여러 번 실행할 수 있다.
- JobExcutionListener
    - Job의 시작과 종료 시 동작을 정의하는 리스너
    - Job의 라이프 사이클 이벤트를 처리할 수 있다.

## 실습

- 주제
    - SpringBatch를 사용한 회원 유효성 검사 및 삭제
    - 회원중 isDeleted가 d인 회원을 삭제하는 로직을 수행한다.
    

### 전체 구성 정리

1. **프로젝트 설정**: Spring Boot와 필요한 의존성을 설정하고 데이터베이스 연결 정보를 설정합니다.
2. **엔티티 설정**: `Member` 엔티티 클래스를 생성합니다.
3. **리포지토리 설정**: `MemberRepository` 인터페이스를 생성하여 JPA를 통해 데이터베이스 작업을 수행합니다.
4. **Spring Batch 설정**: 배치 작업을 구성합니다.
    - **Job Configuration**: 작업(Job)과 단계(Step)을 정의합니다.
    - **Tasklet 설정**: Tasklet을 구현하여 실제 작업을 수행합니다.
    - **Job Listener 설정**: 작업의 시작과 종료 시 동작을 정의합니다.
5. **스케줄러 설정**: 스케줄러를 설정하여 지정된 시간에 배치 작업을 실행합니다.

이제 Spring Boot 애플리케이션을 실행하면 매일 자정에 `isDeleted` 컬럼이 `D`인 회원들을 삭제하는 배치 작업이 실행됩니다. 스케줄러의 크론 표현식을 변경하여 원하는 시간에 작업이 실행되도록 설정할 수 있습니다.