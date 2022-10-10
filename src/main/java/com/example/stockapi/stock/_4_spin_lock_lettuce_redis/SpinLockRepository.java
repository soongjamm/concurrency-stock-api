package com.example.stockapi.stock._4_spin_lock_lettuce_redis;

import com.example.stockapi.stock.LockRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SpinLockRepository implements LockRepository {


    @Override
    public Integer getLock(String key) {
        return null;
    }

    @Override
    public void releaseLock(String key) {

    }
}
