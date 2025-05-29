package com.example.likeherotozero.controller;

import com.example.likeherotozero.repository.Co2EmissionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Co2EmissionRepository repository;

    public AdminController(Co2EmissionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/review")
    public String reviewData(Model model) {
        model.addAttribute("emissions", repository.findByApprovedFalse());
        return "admin/review";
    }

    @PostMapping("/approve/{id}")
    public String approve(@PathVariable Long id) {
        repository.findById(id).ifPresent(emission -> {
            emission.setApproved(true);
            repository.save(emission);
        });
        return "redirect:/admin/review";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/admin/review";
    }
}
