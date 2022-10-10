package com.example.stockapi.stock;

public interface LockRepository {
    Integer getLock(String key);

    void releaseLock(String key);
}
