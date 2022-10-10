package com.example.stockapi.stock._2_pessimistic_optimistic_lock;

import com.example.stockapi.stock.Stock;
import com.example.stockapi.stock.StockRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface PessimisticLockStockRepository extends StockRepository {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Stock findByProductName(String productName);
}
