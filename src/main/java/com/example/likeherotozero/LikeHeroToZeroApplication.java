package com.example.likeherotozero;

import com.example.likeherotozero.repository.Co2EmissionRepository;
import com.example.likeherotozero.repository.ScientistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class LikeHeroToZeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(LikeHeroToZeroApplication.class, args);
    }

    @Bean
    CommandLineRunner runCsvImportOnce(CsvImporter csvImporter, Co2EmissionRepository repository) {
        return args -> {
            // Only run import if the database has no emissions for the year 2023
            if (repository.countByEmissionYear(2023) == 0) {
                csvImporter.importCsv();
                System.out.println("CSV import for 2023 executed.");
            } else {
                System.out.println("CSV data for 2023 already exists – skipping import.");
            }
        };
    }

    @Bean
    CommandLineRunner runInitialSqlIfEmpty(DataSource dataSource, ScientistRepository scientistRepo) {
        return args -> {
            if (scientistRepo.count() == 0) {
                try (Connection conn = dataSource.getConnection()) {
                    ScriptUtils.executeSqlScript(conn, new ClassPathResource("initial-data.sql"));
                    System.out.println("Inserted initial test data.");
                } catch (Exception e) {
                    System.err.println("Failed to insert initial test data: " + e.getMessage());
                }
            }
        };
    }
}
