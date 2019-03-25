package com.training360.cafebabeswebshop.report;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDao {


    private JdbcTemplate jdbcTemplate;

    public ReportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
