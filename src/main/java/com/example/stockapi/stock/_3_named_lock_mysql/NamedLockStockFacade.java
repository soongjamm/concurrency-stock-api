package com.example.stockapi.stock._3_named_lock_mysql;

import com.example.stockapi.stock.StockFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NamedLockStockFacade implements StockFacade {

    private static final Logger LOG = LoggerFactory.getLogger(NamedLockStockFacade.class);

    private final NamedLockRepository namedLockRepository;
    private final NamedLockStockService namedLockStockService;

    public NamedLockStockFacade(NamedLockRepository namedLockRepository, NamedLockStockService namedLockStockService) {
        this.namedLockRepository = namedLockRepository;
        this.namedLockStockService = namedLockStockService;
    }

    public void deduction(String productName) {
        try {
            Integer result = namedLockRepository.getLock(productName.replace("상품", "product"));
            LOG.info("get_lock() result = {}", result);
            namedLockStockService.deduction(productName);
        } finally {
            namedLockRepository.releaseLock(productName.replace("상품", "product"));
        }

    }
}
