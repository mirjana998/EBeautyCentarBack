package com.example.ebeautycentar.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalTime;

public class SalonUslugaDodajDto implements Serializable {

    private Long salonId;
    private Long uslugaId;
    private String trajanjeUsluge;
    private Double cijena;
    private String opis;

    public SalonUslugaDodajDto(){}

    public SalonUslugaDodajDto(Long salonId, Long uslugaId, String trajanjeUsluge, Double cijena, String opis) {
        this.salonId = salonId;
        this.uslugaId = uslugaId;
        this.trajanjeUsluge = trajanjeUsluge;
        this.cijena = cijena;
        this.opis = opis;
    }

    public Long getSalonaId() {
        return salonId;
    }

    public Long getUslugaId() {
        return uslugaId;
    }

    public String getTrajanjeUsluge() {
        return trajanjeUsluge;
    }

    public Double getCijena() {
        return cijena;
    }

    public String getOpis() {
        return opis;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }

    public void setUslugaId(Long uslugaId) {
        this.uslugaId = uslugaId;
    }

    public void setTrajanjeUsluge(String trajanjeUsluge) {
        this.trajanjeUsluge = trajanjeUsluge;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
