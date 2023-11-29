package com.backend.dataenrichment.constants;

public class DataenrichmentConstants {
  public static class WebScrapingConstants {
    public static final String CURRENT_PORTFOLIO_URL = "https://eqtgroup.com/current-portfolio/";
    public static final String TABLE_QUERY = "li.flex.flex-col.border-t.border-neutral-light";
    public static final String NAME_QUERY = "span.relative.inline-flex.items-center.pb-2.transition-hover.font-t-bold.text-t-400.text-secondary-darker";
    public static final String SECTOR_QUERY = "span.font-medium:contains(Sector)";
    public static final String COUNTRY_QUERY = "span.font-medium:contains(Country)";
    public static final String ENTRY_QUERY = "span.font-medium:contains(Entry)";
    public static final String FUND_QUERY = "ul li a.text-primary.font-medium";
    public static final String FUND_LIST_QUERY = "span.font-medium:contains(Fund)";
  }

  public static class GCSFetchingConstants {
    public static final String BUCKET_NAME = "motherbrain-external-test";
    public static final String OBJECT_NAME = "interview-test-org.json.gz";


  }

}
