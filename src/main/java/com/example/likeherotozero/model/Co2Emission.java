package com.example.likeherotozero.model;

import jakarta.persistence.*;

@Entity
public class Co2Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private Integer emissionYear;
    private long emissionKt;

    public Co2Emission() {
    }

    public Co2Emission(String country, Integer emissionYear, long emissionKt) {
        this.country = country;
        this.emissionYear = emissionYear;
        this.emissionKt = emissionKt;
    }

    @Column(nullable = false)
    private boolean approved = false;

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getEmissionYear() {
        return emissionYear;
    }

    public void setEmissionYear(Integer emissionYear) {
        this.emissionYear = emissionYear;
    }

    public long getEmissionKt() {
        return emissionKt;
    }

    public void setEmissionKt(long emissionKt) {
        this.emissionKt = emissionKt;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
