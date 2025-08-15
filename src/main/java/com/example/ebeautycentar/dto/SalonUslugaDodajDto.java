package com.example.ebeautycentar.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalTime;

public class SalonUslugaDodajDto implements Serializable {

    private String nazivSalona;
    private String nazivUsluge;
    private LocalTime trajanje_usluge;
    private Double cijena;
    private String opis;

    public SalonUslugaDodajDto(){}
    public SalonUslugaDodajDto(String nazivSalona, String nazivUsluge, LocalTime trajanje_usluge, Double cijena, String opis) {
        this.nazivSalona = nazivSalona;
        this.nazivUsluge = nazivUsluge;
        this.trajanje_usluge = trajanje_usluge;
        this.cijena = cijena;
        this.opis = opis;
    }


    public String getNazivSalona() {
        return nazivSalona;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public LocalTime getTrajanje_usluge() {
        return trajanje_usluge;
    }

    public Double getCijena() {
        return cijena;
    }

    public String getOpis() {
        return opis;
    }

    public void setNazivSalona(String nazivSalona) {
        this.nazivSalona = nazivSalona;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public void setTrajanje_usluge(LocalTime trajanje_usluge) {
        this.trajanje_usluge = trajanje_usluge;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
