package com.example.likeherotozero;

import com.example.likeherotozero.model.Co2Emission;
import com.example.likeherotozero.repository.Co2EmissionRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class CsvImporter {

    private final Co2EmissionRepository repository;

    public CsvImporter(Co2EmissionRepository repository) {
        this.repository = repository;
    }

    public void importCsv() throws IOException {
        InputStream input = new ClassPathResource("emissions.csv").getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] columns = line.split(",");
                if (columns.length >= 2) {
                    String country = columns[0].trim();
                    String rawValue = columns[1].trim().replace(".", "").replace(",", ".");
                    try {
                        double emission = Double.parseDouble(rawValue);
                        Co2Emission co2 = new Co2Emission();
                        co2.setCountry(country);
                        co2.setEmissionYear(2023);
                        co2.setEmissionKt((long) emission);
                        co2.setApproved(true);
                        repository.save(co2);
                    } catch (NumberFormatException e) {
                        System.err.println("Could not parse emission value: " + rawValue);
                    }
                }
            }
        }
    }
}
