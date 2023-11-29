package com.backend.dataenrichment.models;

import java.util.List;
import lombok.Data;

@Data
public class Company {
  String name;
  String sector;
  String country;
  List<String> funds;
  String entry;
}
