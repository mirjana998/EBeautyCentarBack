package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.SalonUsluga;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * DTO for {@link SalonUsluga}
 */
public class SalonUslugaDto  implements Serializable {
    private  Long id;
    private Long salonId;
    private  Long uslugaId;
    private LocalDate datumPocetka;
    private LocalTime trajanjeUsluge;
    private LocalDate datumKraja;
    private  String status;
    private Double cijena;
    private String opis;
    private String slika;

    public SalonUslugaDto(Long id, Long salonId, Long uslugaId, LocalDate datumPocetka, LocalTime trajanjeUsluge, LocalDate datumKraja, String status, Double cijena, String opis,String slika) {
        this.id = id;
        this.salonId = salonId;
        this.uslugaId = uslugaId;
        this.datumPocetka = datumPocetka;
        this.trajanjeUsluge = trajanjeUsluge;
        this.datumKraja = datumKraja;
        this.status = status;
        this.cijena = cijena;
        this.opis = opis;
        this.slika = slika;
    }

    public SalonUslugaDto(SalonUsluga salonUsluga) {
        this.id = salonUsluga.getId();
        this.salonId = salonUsluga.getSalon().getId();
        this.uslugaId = salonUsluga.getUsluga().getId();
        this.datumKraja = salonUsluga.getDatumKraja();
        this.datumPocetka = salonUsluga.getDatumPocetka();
        this.status = salonUsluga.getStatus();
        this.cijena = salonUsluga.getCijena();
        this.opis = salonUsluga.getOpis();
        this.trajanjeUsluge = salonUsluga.getTrajanjeUsluge();
        this.slika = null;
    }

    public Long getId() {
        return id;
    }

    public Long getSalonId() {
        return salonId;
    }

    public Long getUslugaId() {
        return uslugaId;
    }

    public LocalDate getDatumPocetka() {
        return datumPocetka;
    }

    public LocalTime getTrajanjeUsluge() {
        return trajanjeUsluge;
    }

    public LocalDate getDatumKraja() {
        return datumKraja;
    }

    public String getStatus() {
        return status;
    }

    public Double getCijena() {
        return cijena;
    }

    public String getOpis() {
        return opis;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getSlika() {
        return slika;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDatumKraja(LocalDate datumKraja) {
        this.datumKraja = datumKraja;
    }

    public void setTrajanjeUsluge(LocalTime trajanjeUsluge) {
        this.trajanjeUsluge = trajanjeUsluge;
    }

    public void setDatumPocetka(LocalDate datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public void setUslugaId(Long uslugaId) {
        this.uslugaId = uslugaId;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalonUslugaDto entity = (SalonUslugaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.salonId, entity.salonId) &&
                Objects.equals(this.uslugaId, entity.uslugaId) &&
                Objects.equals(this.datumPocetka, entity.datumPocetka) &&
                Objects.equals(this.trajanjeUsluge, entity.trajanjeUsluge) &&
                Objects.equals(this.datumKraja, entity.datumKraja) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.cijena, entity.cijena) &&
                Objects.equals(this.opis, entity.opis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salonId, uslugaId, datumPocetka, trajanjeUsluge, datumKraja, status, cijena, opis);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "salonId = " + salonId + ", " +
                "uslugaId = " + uslugaId + ", " +
                "datumPocetka = " + datumPocetka + ", " +
                "trajanjeUsluge = " + trajanjeUsluge + ", " +
                "datumKraja = " + datumKraja + ", " +
                "status = " + status + ", " +
                "cijena = " + cijena + ", " +
                "opis = " + opis + ")";
    }
}