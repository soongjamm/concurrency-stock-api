
### Pessimistic Lock
- 데이터베이스에서 Exclusive Lock 을 잡아 동시성을 제어한다. 
- 따라서 안전하게 동시성을 제어할 수 있다.
- MySQL 기준 `SELECT for update` 쿼리를 사용하면 되는데, JPA 는 쿼리를 작성하지 않고 `@Lock(LockModeType.PESSIMISTIC_WRITE)` 으로 사용한다.

**단점**
- DB 락을 잡기 때문에 병목이 생기므로 성능이 떨어진다.   
  (그러나 충돌이 잦다면 오히려 Optimistic Lock 보다 성능이 좋을 수 있다.)
- 데드락이 발생할 수 있다.

### Optimistic Lock
- 애플리케이션 레벨에서 버저닝을 이용해 락을 잡는다.
- 실제 DB락이 아니라서 병목이 없으므로 속도가 빠르다.
- JPA 에서 `@Version` 을 사용한다.

**단점**
- 재처리 로직이 필요하다.
- 만약 충돌이 자주 발생한다면 오히려 DB 락을 잡는 것 보다 성능에 문제가 있을 수 있다.
  - 예를들어 `OptimisticStockTest.java` 같이 무조건 레이스컨디션이 발생하거나 선착순 이벤트 등에서는 비효율적이다.
  - Pessimistic Lock은 1초대 였지만, Optimistic Lock은 거의 5초에 가까웠다.