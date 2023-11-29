package com.backend.dataenrichment.controller;

import com.backend.dataenrichment.models.Company;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
@Slf4j
public class DataenrichmentDataToFile {
  @GetMapping("/saveData")
  public void saveDataToFile(@RequestBody List<Company> companies) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String json = objectMapper.writeValueAsString(companies);
      System.out.println(json);
      try (FileWriter fileWriter = new FileWriter("company_data.json")) {
        fileWriter.write(json);
      } catch (IOException e) {
        log.error("Something went wrong while writing JSON to file", e);
      }
      log.info("Data saved to file successfully");
    } catch (IOException e) {
      log.error("Something went wrong while converting data to JSON", e);
    }
  }

  @GetMapping("/retrieve")
  public String retrieveData() {
    try {
      String content = new String(Files.readAllBytes(Paths.get("company_data.json")));
      return content;
    } catch (IOException e) {
      log.error("Something went wrong reading data from file", e);
      return "Error reading from file";
    }
  }
}
