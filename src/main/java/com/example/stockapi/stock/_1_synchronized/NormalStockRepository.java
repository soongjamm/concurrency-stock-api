package com.example.stockapi.stock._1_synchronized;

import com.example.stockapi.stock.Stock;
import com.example.stockapi.stock.StockRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalStockRepository extends StockRepository {

    Stock findByProductName(String productName);
}
