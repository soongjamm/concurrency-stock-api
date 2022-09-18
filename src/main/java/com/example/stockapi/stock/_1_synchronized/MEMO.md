
### 방법
- 동시성을 제어하기 위한 가장 간단한 해결책으로 `synchronized` 키워드를 사용한다.  

### 문제점
- `@Transactional` 을 붙이면 synchronized 가 원하는대로 동작하지 않는다.
  - 스프링 AOP에 의해 Proxy 객체가 사용되고, Proxy 객체에는 synchronized 가 적용되있지 않은 상태이다.
    - commit 은 synchronized 블록 밖에서 일어나는 일이다.
    - T1 에서 synchronized 블록 밖으로 빠져나와서 프록시 객체가 commit 하기 직전에  
      T2 에서 synchronized 블록 진입 후 read 를 하면 정합성이 깨지게 된다.
  - 단일 프로세스에서 동작하기 때문에 인스턴스가 여러 대인 경우 동시성 제어가 되지 않는다.
