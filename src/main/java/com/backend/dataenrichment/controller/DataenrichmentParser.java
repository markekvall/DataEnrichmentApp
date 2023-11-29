package com.backend.dataenrichment.controller;

import com.backend.dataenrichment.external.CurrentPortfolioScrapingAgent;
import com.backend.dataenrichment.external.OrgReferenceDataGCSAgent;
import com.backend.dataenrichment.models.Company;
import com.backend.dataenrichment.models.CompanyCloudInfo;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataenrichmentParser {

  CurrentPortfolioScrapingAgent currentPortfolioScrapingAgent;
  OrgReferenceDataGCSAgent gCSAgent;

  public DataenrichmentParser() {
    currentPortfolioScrapingAgent = new CurrentPortfolioScrapingAgent();
    gCSAgent = new OrgReferenceDataGCSAgent();

  }

  public List<Company> parseTables() {

    List<Company> companies = currentPortfolioScrapingAgent.scrapeCurrentPortfolioTable();

    Set<String> nameSet = companies.stream().map(Company::getName).collect(Collectors.toSet());

    List<CompanyCloudInfo> companyCloudInfos = gCSAgent.readFromGCP(nameSet); //not used in final data set. But is a list of companies that matched on names.

    return companies;

    //read from interview-test-org.json.gz to fetch company info to companies that exsists in companies list.

    //read from interview-test-org-2019.json.gz and add info to companies that exsists in the companies list.

    //read from interview-test-funding and ...funding-2019 and add funding object to companies list.

  }
}
