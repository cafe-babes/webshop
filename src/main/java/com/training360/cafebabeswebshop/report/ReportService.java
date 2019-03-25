package com.training360.cafebabeswebshop.report;


import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private ReportDao reportDao;

    public ReportService(ReportDao reportDao) {
        this.reportDao = reportDao;
    }
}
