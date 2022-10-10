package com.example.stockapi.stock._5_pub_sub_redisson_redis;

import com.example.stockapi.stock.StockFacade;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PubSubLockStockFacade implements StockFacade {

    private static final Logger LOG = LoggerFactory.getLogger(PubSubLockStockFacade.class);
    private final RedissonClient redissonClient;
    private final PubSubLockStockService pubSubLockStockService;

    public PubSubLockStockFacade(RedissonClient redissonClient, PubSubLockStockService pubSubLockStockService) {
        this.redissonClient = redissonClient;
        this.pubSubLockStockService = pubSubLockStockService;
    }

    public void deduction(String productName) {
        RLock lock = redissonClient.getLock(productName);

        try {
            if (failedToAcquireLock(lock)) {
                throw new IllegalStateException("Redisson 락 획득 실패");
            }
            pubSubLockStockService.deduction(productName);
        } catch (Exception e) {
            LOG.error("재고 차감 중 에러 발생: {}", e.getMessage());
        } finally {
            lock.unlock();
        }

    }

    private boolean failedToAcquireLock(RLock lock) throws InterruptedException {
        return !lock.tryLock(15, 1, TimeUnit.SECONDS); // 락 획득을 위해 5초까지 대기, 획득 후 1초 타임아웃 설정
    }

}
