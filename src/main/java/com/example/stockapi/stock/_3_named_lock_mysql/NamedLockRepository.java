package com.example.stockapi.stock._3_named_lock_mysql;

import com.example.stockapi.stock.LockRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NamedLockRepository implements LockRepository {

    private final JdbcTemplate jdbcTemplate;

    public NamedLockRepository() {
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl("jdbc:mysql://localhost:1500/stock");
        configuration.setUsername("root");
        configuration.setMaximumPoolSize(10);
        this.jdbcTemplate = new JdbcTemplate(new HikariDataSource(configuration));
    }

    public Integer getLock(@Param("key") String key) {
        return jdbcTemplate.queryForObject("SELECT get_lock('" + key + "', 1000)", Integer.class);
    }

    public void releaseLock(@Param("key") String key) {
        jdbcTemplate.query("SELECT release_lock('" + key + "')", (rs, rowNum) -> new Object());
    }
}
