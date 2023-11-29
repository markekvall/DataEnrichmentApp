package com.backend.dataenrichment.controller;

import com.backend.dataenrichment.models.Company;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataenrichmentController implements CommandLineRunner {
    @Override
    public void run(String... args) {

      DataenrichmentParser scraper = new DataenrichmentParser();
      DataenrichmentDataToFile dataenrichmentDataToFile = new DataenrichmentDataToFile();

      List<Company> companies = scraper.parseTables();
      dataenrichmentDataToFile.saveDataToFile(companies);
    }
}
