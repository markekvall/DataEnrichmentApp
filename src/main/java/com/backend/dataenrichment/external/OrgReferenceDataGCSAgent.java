package com.backend.dataenrichment.external;

import static com.backend.dataenrichment.constants.DataenrichmentConstants.GCSFetchingConstants.BUCKET_NAME;
import static com.backend.dataenrichment.constants.DataenrichmentConstants.GCSFetchingConstants.OBJECT_NAME;

import com.backend.dataenrichment.models.CompanyCloudInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrgReferenceDataGCSAgent {

  public List<CompanyCloudInfo> readFromGCP(Set<String> companyNameSet) {
    List<CompanyCloudInfo> companyCloudInfos = new ArrayList<>();
    try {

      Storage storage = StorageOptions.newBuilder()
          .build()
          .getService();
      Blob blob = storage.get(BUCKET_NAME, OBJECT_NAME);

      parseGCSCompanyData(blob, companyNameSet,  companyCloudInfos);

    } catch (StorageException e) {
      log.error("Something went wrong while fetching data from GCS", e);
      }

    return companyCloudInfos;
  }

  private void parseGCSCompanyData(Blob blob, Set<String> companyNameSet, List<CompanyCloudInfo> companyCloudInfos) {
    if (blob != null) {
      try (GZIPInputStream gzipInputStream =
          new GZIPInputStream(Channels.newInputStream(blob.reader()));
          BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8))) {
        ObjectMapper objectMapper = new ObjectMapper();
        String line;

        while ((line = reader.readLine()) != null) {
          CompanyCloudInfo cloudInfo = objectMapper.readValue(line, CompanyCloudInfo.class);
          if (isCompanyNameExactMatch(companyNameSet, cloudInfo.getName())) {
            log.info("Portfolio company name found in GCS dataset: " + cloudInfo.getName());
            companyCloudInfos.add(cloudInfo);
          }
        }
        log.info("Found: " + companyCloudInfos.size() + " matches in total!");

      } catch (IOException e) {
        log.error("Something went wrong while parsing GCS company data", e);
      }
    }
  }

  private boolean isCompanyNameExactMatch(Set<String> companyNames, String targetCompanyName) {
    return companyNames.stream()
            .map(String::trim)
        .anyMatch(name -> name.equals(targetCompanyName));

  }
}
