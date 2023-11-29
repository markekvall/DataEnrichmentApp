package com.backend.dataenrichment.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompanyCloudInfo {
  private final String uuid;
  private final String name;
  private final String homepageUrl;
  private final String countryCode;
  private final String city;
  private final String foundedOn;
  private final String shortDescription;
  private final String employeeCount;
  private final String numFundingRounds;
  private final String lastFundingOn;
  private final String totalFundingUsd;

  @JsonCreator
  public CompanyCloudInfo(
      @JsonProperty("uuid") String uuid,
      @JsonProperty("name") String name,
      @JsonProperty("homepage_url") String homepageUrl,
      @JsonProperty("country_code") String countryCode,
      @JsonProperty("city") String city,
      @JsonProperty("founded_on") String foundedOn,
      @JsonProperty("short_description") String shortDescription,
      @JsonProperty("employee_count") String employeeCount,
      @JsonProperty("num_funding_rounds") String numFundingRounds,
      @JsonProperty("last_funding_on") String lastFundingOn,
      @JsonProperty("total_funding_usd") String totalFundingUsd) {
    this.uuid = uuid;
    this.name = name;
    this.homepageUrl = homepageUrl;
    this.countryCode = countryCode;
    this.city = city;
    this.foundedOn = foundedOn;
    this.shortDescription = shortDescription;
    this.employeeCount = employeeCount;
    this.numFundingRounds = numFundingRounds;
    this.lastFundingOn = lastFundingOn;
    this.totalFundingUsd = totalFundingUsd;
  }
}
