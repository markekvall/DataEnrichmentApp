package com.backend.dataenrichment.external;

import static com.backend.dataenrichment.constants.DataenrichmentConstants.WebScrapingConstants.COUNTRY_QUERY;
import static com.backend.dataenrichment.constants.DataenrichmentConstants.WebScrapingConstants.CURRENT_PORTFOLIO_URL;
import static com.backend.dataenrichment.constants.DataenrichmentConstants.WebScrapingConstants.ENTRY_QUERY;
import static com.backend.dataenrichment.constants.DataenrichmentConstants.WebScrapingConstants.FUND_LIST_QUERY;
import static com.backend.dataenrichment.constants.DataenrichmentConstants.WebScrapingConstants.FUND_QUERY;
import static com.backend.dataenrichment.constants.DataenrichmentConstants.WebScrapingConstants.NAME_QUERY;
import static com.backend.dataenrichment.constants.DataenrichmentConstants.WebScrapingConstants.SECTOR_QUERY;
import static com.backend.dataenrichment.constants.DataenrichmentConstants.WebScrapingConstants.TABLE_QUERY;

import com.backend.dataenrichment.models.Company;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
public class CurrentPortfolioScrapingAgent {

  public List<Company> scrapeCurrentPortfolioTable() {
    List<Company> companies = new ArrayList<>();
    try {
      final Document document = Jsoup.connect(CURRENT_PORTFOLIO_URL).get();
      Elements companyElements = document.select(TABLE_QUERY);

      for (Element companyElement : companyElements) {
        Element companyNameElement = companyElement.selectFirst(NAME_QUERY);

        Company company = new Company();
        company.setName(companyNameElement.text().trim());
        company.setSector(getValueForLabel(companyElement, SECTOR_QUERY));
        company.setCountry(getValueForLabel(companyElement, COUNTRY_QUERY));
        company.setFunds(getFundList(companyElement));
        company.setEntry(getValueForLabel(companyElement, ENTRY_QUERY));
        companies.add(company);
        log.info("Parsing company: " + company.getName() + " - sector: " + company.getSector() + " - country: " + company.getCountry() + " - entry: " + company.getEntry());
      }
      log.info("Successfully parsed: " + companies.size() + " companies");

    } catch (Exception e) {
      log.error("Something went wrong while scraping portfolio data");
      e.printStackTrace();
    }
    return companies;
  }

  private List<String> getFundList(Element listItem) {
    List<String> fundList = new ArrayList<>();
    Element fundSection = listItem.selectFirst(FUND_LIST_QUERY);
    if (fundSection != null) {
      Elements fundDetails = fundSection.parent().select(FUND_QUERY);
      for (Element fundDetail : fundDetails) {
        String fund = fundDetail.text().trim();
        log.info("fund: " + fund);
        fundList.add(fund);
      }
    }
    return fundList;
  }

  private static String getValueForLabel(Element listItem, String query) {
    Element labelElement = listItem.selectFirst(query).nextElementSibling();
    return labelElement != null ? labelElement.text().trim() : "";
  }
}
