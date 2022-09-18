package com.example.stockapi.stock._2_pessimistic_optimistic_lock;

import com.example.stockapi.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface OptimisticLockRepository extends JpaRepository<OptimisticLockStock, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    Stock findByProductName(String productName);
}
