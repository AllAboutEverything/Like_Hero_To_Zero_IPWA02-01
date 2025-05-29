package com.example.likeherotozero.repository;
import com.example.likeherotozero.model.Co2Emission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface Co2EmissionRepository extends JpaRepository<Co2Emission, Long> {
    List<Co2Emission> findByApprovedFalse();
    List<Co2Emission> findTop10ByApprovedTrueOrderByEmissionKtDesc();
    Optional<Co2Emission> findTopByCountryIgnoreCaseAndApprovedIsTrueOrderByEmissionYearDesc(String country);
    long countByEmissionYear(int emissionYear);
}
