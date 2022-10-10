# Redis Redisson PubSub Lock
> Lettuce 는 Redis Client Library 이다.

`_4_spin_lock_lettuce_redis` 와 마찬가지로 Redis 로 분산락을 구현하지만, 클라이언트 라이브러리로 Redisson을 사용하고, PubSub 방식으로 Lock/Unlock 을 한다.  
lettuce의 spin lock 방식에서는 직접 retry 로직을 구현해줘야 하는 것이 단점이었다. 그리고 Redis 에 부하가 생길 위험이 있었다.  
그러나 Redisson 을 이용하면 Pub-Sub 방식으로 사용할 수 있어서 지속적으로 락 획득을 위한 요청을 하지 않아도 되므로 부하를 주지 않는다. retry 로직을 구현할 필요도 없다.  
