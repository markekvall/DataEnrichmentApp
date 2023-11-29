package com.backend.dataenrichment.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataenrichmentController implements CommandLineRunner {
    @Override
    public void run(String... args) {

      DataenrichmentParser scraper = new DataenrichmentParser();
      scraper.parseTables();

    }


}
