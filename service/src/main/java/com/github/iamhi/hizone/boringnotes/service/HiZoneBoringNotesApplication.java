package com.github.iamhi.hizone.boringnotes.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.github.iamhi.hizone.boringnotes"})
@EnableReactiveMongoRepositories("com.github.iamhi.hizone.boringnotes.out")
@ConfigurationPropertiesScan("com.github.iamhi.hiintegration.hizone.boringnotes.config")
public class HiZoneBoringNotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiZoneBoringNotesApplication.class, args);
	}
}
