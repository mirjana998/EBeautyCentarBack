package com.example.ebeautycentar.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "salon_usluga")
public class SalonUsluga {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "salon_id", nullable = false)
    private Salon salon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usluga_id", nullable = false)
    private Usluga usluga;

    @Column(name = "datum_pocetka")
    private LocalDate datumPocetka;

    @Column(name = "trajanje_usluge", nullable = false)
    private Integer trajanjeUsluge;

    @Column(name = "datum_kraja")
    private LocalDate datumKraja;

    @ColumnDefault("'A'")
    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @Column(name = "cijena", nullable = false)
    private Double cijena;

    @Column(name = "opis", length = 200)
    private String opis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public LocalDate getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(LocalDate datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Integer getTrajanjeUsluge() {
        return trajanjeUsluge;
    }

    public void setTrajanjeUsluge(Integer trajanjeUsluge) {
        this.trajanjeUsluge = trajanjeUsluge;
    }

    public LocalDate getDatumKraja() {
        return datumKraja;
    }

    public void setDatumKraja(LocalDate datumKraja) {
        this.datumKraja = datumKraja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

}