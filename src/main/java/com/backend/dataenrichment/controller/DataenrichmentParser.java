package com.backend.dataenrichment.controller;

import com.backend.dataenrichment.external.CurrentPortfolioScrapingAgent;
import com.backend.dataenrichment.models.Company;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataenrichmentParser {

  public void parseTables() {
    CurrentPortfolioScrapingAgent currentPortfolioScrapingAgent = new CurrentPortfolioScrapingAgent();
    List<Company> companies = currentPortfolioScrapingAgent.scrapeCurrentPortfolioTable();
  }


}
