package com.training360.cafebabeswebshop.report;

import org.springframework.jdbc.core.JdbcTemplate;

public class ReportDao {


    JdbcTemplate jdbcTemplate;

    public ReportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
