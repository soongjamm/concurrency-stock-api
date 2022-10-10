# Redis Lettuce Spin Lock
> Lettuce 는 Redis Client Library 이다.

이 방식은 Redis 가 싱글 스레드로 동작한다는 특징과 setnx(SET if Nox Exist) 명령을 이용해 분산락(Distributed Lock)을 구현한 것이다.  
분산락이기 때문에 여러 서버 인스턴스의 락을 제어할 수 있다는 장점이 있다.
MySQL의 get_lock() 으로 구현한 분산락과 비교했을 때 Redis의 장점은 세션 관리를 하지 않아도 된다는 점이다.  
'get_lock()'의 세션 관리를 알아보다가 포기한 상황에서 엄청 큰 장점이라 생각한다. 구현하는 수고도 들지않고, 안전하다.
그리고 setnx 명령으로 timeout 도 설정할 수 있기 때문에 락 획득을 위해 무한 대기할 위험도 없다.

단점은 Spin Lock 방식이다. Spin Lock 은 락 획득시 까지 루프를 돌아야 한다. 즉 개발자가 retry 로직을 구현해주어야 한다.  
또한 여러 요청이 동시에 루프를 돌며 락 획득을 위한 요청을 하기때문에 Redis가 받는 부하를 조절하기 위해 인터벌을 두는 등의 조치를 취해줘야 한다.  
현재 코드에서는 `sleep` 으로 인터벌을 주었다.

