package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.SalonUsluga;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private  UslugaDto uslugaDto;
    private LocalDate datumPocetka;
    private LocalTime trajanjeUsluge;
    private LocalDate datumKraja;
    private  String status;
    private Double cijena;
    private String opis;
    private String slika;

    @JsonCreator
    public SalonUslugaDto(
            @JsonProperty("id") Long id,
            @JsonProperty("salonId") Long salonId,
            @JsonProperty("uslugaDto") UslugaDto uslugaDto,
            @JsonProperty("datumPocetka") LocalDate datumPocetka,
            @JsonProperty("trajanjeUsluge") LocalTime trajanjeUsluge,
            @JsonProperty("datumKraja") LocalDate datumKraja,
            @JsonProperty("status") String status,
            @JsonProperty("cijena") Double cijena,
            @JsonProperty("opis") String opis,
            @JsonProperty("slika") String slika
    ) {
        this.id = id;
        this.salonId = salonId;
        this.uslugaDto = uslugaDto;
        this.datumPocetka = datumPocetka;
        this.trajanjeUsluge = trajanjeUsluge;
        this.datumKraja = datumKraja;
        this.status = status;
        this.cijena = cijena;
        this.opis = opis;
        this.slika = slika;
    }

    public SalonUslugaDto(Long id, Long salonId, Long uslugaId, LocalDate datumPocetka, LocalTime trajanjeUsluge, LocalDate datumKraja, String status, Double cijena, String opis,String slika) {
        this.id = id;
        this.salonId = salonId;
        this.uslugaDto = null;
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
        this.uslugaDto = new UslugaDto(salonUsluga.getUsluga());
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

    public UslugaDto getUslugaDto() {
        return uslugaDto;
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

    public void setUslugaDto(UslugaDto uslugaDto) {
        this.uslugaDto = uslugaDto;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SalonUslugaDto{" +
                "id=" + id +
                ", salonId=" + salonId +
                ", uslugaDto=" + uslugaDto +
                ", datumPocetka=" + datumPocetka +
                ", trajanjeUsluge=" + trajanjeUsluge +
                ", datumKraja=" + datumKraja +
                ", status='" + status + '\'' +
                ", cijena=" + cijena +
                ", opis='" + opis + '\'' +
                ", slika='" + slika + '\'' +
                '}';
    }

}