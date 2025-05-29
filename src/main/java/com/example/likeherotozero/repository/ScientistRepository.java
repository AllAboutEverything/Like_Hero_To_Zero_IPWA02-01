package com.example.likeherotozero.repository;

import com.example.likeherotozero.model.Scientist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScientistRepository extends JpaRepository<Scientist, Long> {
    Optional<Scientist> findByUsername(String username);
}
