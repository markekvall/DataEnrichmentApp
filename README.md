# DataEnrichmentApp

Project created to build a pipeline that enriches the information on EQT portfolio companies
scraped from our public website with the data from another dataset that is provided. The goal is to
export the resulting enriched dataset to a storage location of your choice in a data model that
you design.

**Prerequisites**

- `java installed on your local machine (i use vesion 17)`
- `access to localhost gate 8100`
- `build maven pom.xml file, and see if all dependencies matches up.`

## Local development
run application by navigating to, and run:
```
com/backend/dataenrichment/DataenrichmentApplication.java
```

The application will then take the following steps:

- It will scrape company data from `eqtgroup.com/current-portfolio`
- It then parses this data into a list of data objects called "Company"
- After that, the application retrieves the data from GCS. And retrieves company info from companies with the same name as the EQT portfolio companies.
- The data of the company data with the same names are saved in a list but not handled further.
- The output data is just the data scraped from the eqt website. It is saved in file `company_data.json` that will be created in the root of the repo when application is run
- The Json can also be retrieved by visiting `http://localhost:8100/api/data/retrieve` when application is running.

