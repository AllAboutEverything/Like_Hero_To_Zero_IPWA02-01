package com.example.likeherotozero.controller;

import com.example.likeherotozero.model.Co2Emission;
import com.example.likeherotozero.repository.Co2EmissionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final Co2EmissionRepository repository;

    public HomeController(Co2EmissionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String homepage(@RequestParam(required = false) String country, Model model) {
        if (country != null && !country.isEmpty()) {
            Optional<Co2Emission> emission = repository.findTopByCountryIgnoreCaseAndApprovedIsTrueOrderByEmissionYearDesc(country);
            model.addAttribute("emissionYear", emission.map(Co2Emission::getEmissionYear).orElse(null));
            model.addAttribute("emission", emission.orElse(null));
            model.addAttribute("country", country);
        }

        List<Co2Emission> top10 = repository.findTop10ByApprovedTrueOrderByEmissionKtDesc();
        model.addAttribute("top10emissions", top10);

        return "home";
    }
}
