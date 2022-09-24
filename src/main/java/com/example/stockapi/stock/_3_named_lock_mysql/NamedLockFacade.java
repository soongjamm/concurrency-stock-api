package com.example.stockapi.stock._3_named_lock_mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NamedLockFacade {

    private static final Logger LOG = LoggerFactory.getLogger(NamedLockFacade.class);

    private final NamedLockRepository namedLockRepository;
    private final NamedLockService namedLockService;

    public NamedLockFacade(NamedLockRepository namedLockRepository, NamedLockService namedLockService) {
        this.namedLockRepository = namedLockRepository;
        this.namedLockService = namedLockService;
    }

    public void deduction(String productName) {
        try {
            Integer result = namedLockRepository.getLock(productName.replace("상품", "product"));
            LOG.info("get_lock() result = {}", result);
            namedLockService.deduction(productName);
        } finally {
            namedLockRepository.releaseLock(productName.replace("상품", "product"));
        }

    }
}
