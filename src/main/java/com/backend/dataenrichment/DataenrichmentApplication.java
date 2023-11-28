package com.backend.dataenrichment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DataenrichmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataenrichmentApplication.class, args);
	}

	@Component
	class BookingCommandLineRunner implements CommandLineRunner {

		@Override
		public void run(String... args) {
			System.out.println("hello world");
		}
	}

}
