package com.example.stockapi.stock._4_spin_lock_lettuce_redis;

import com.example.stockapi.stock.LockRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Repository
public class SpinLockRepository implements LockRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public SpinLockRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean getLock(String key) {
        Boolean result = redisTemplate
                .opsForValue()
                .setIfAbsent(key, "lock", Duration.of(3, ChronoUnit.SECONDS));
        return Objects.requireNonNull(result, "failed to get lock");
    }

    @Override
    public void releaseLock(String key) {
        redisTemplate.delete(key);
    }
}
