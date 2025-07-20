package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.SalonUsluga;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link SalonUsluga}
 */
public class SalonUslugaDto implements Serializable {
    private final Long id;
    private final Long salonId;
    private final Long uslugaId;
    private final LocalDate datumPocetka;
    private final Instant trajanjeUsluge;
    private final LocalDate datumKraja;
    private final String status;
    private final Double cijena;
    private final String opis;

    public SalonUslugaDto(Long id, Long salonId, Long uslugaId, LocalDate datumPocetka, Instant trajanjeUsluge, LocalDate datumKraja, String status, Double cijena, String opis) {
        this.id = id;
        this.salonId = salonId;
        this.uslugaId = uslugaId;
        this.datumPocetka = datumPocetka;
        this.trajanjeUsluge = trajanjeUsluge;
        this.datumKraja = datumKraja;
        this.status = status;
        this.cijena = cijena;
        this.opis = opis;
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

    public Instant getTrajanjeUsluge() {
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