package com.example.stockapi.stock;

public interface LockRepository {
    boolean getLock(String key);

    void releaseLock(String key);
}
