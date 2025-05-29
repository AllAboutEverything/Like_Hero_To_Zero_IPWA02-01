package com.example.likeherotozero.controller;

import com.example.likeherotozero.model.Co2Emission;
import com.example.likeherotozero.repository.Co2EmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddEmissionController {

    private final Co2EmissionRepository repository;

    @Autowired
    public AddEmissionController(Co2EmissionRepository repository) {
        this.repository = repository;
    }

    // GET-Mapping für /add - Formular anzeigen
    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("emission", new Co2Emission());
        return "add";
    }

    // POST-Mapping für /add - Formular absenden und speichern
    @PostMapping("/add")
    public String submitForm(@ModelAttribute Co2Emission emission, Model model) {
        emission.setApproved(false);
        repository.save(emission);
        model.addAttribute("message", "Datensatz erfolgreich gespeichert!");
        // Damit Formular nach Absenden wieder angezeigt wird (evtl. leeres Formular)
        model.addAttribute("emission", new Co2Emission());
        return "add";
    }
}
