package com.example.ebeautycentar.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "salon")
public class Salon {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "naziv", nullable = false, length = 45)
    private String naziv;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "broj_telefona", nullable = false, length = 20)
    private String brojTelefona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tip_id", nullable = false)
    private Tip tip;

    @Column(name = "datum_otvaranja")
    private LocalDate datumOtvaranja;

    @ColumnDefault("'A'")
    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @Column(name = "datum_zatvaranja")
    private LocalDate datumZatvaranja;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vlasnik_salona_id", nullable = false)
    private VlasnikSalona vlasnikSalona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lokacija_id", nullable = false)
    private Lokacija lokacija;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public LocalDate getDatumOtvaranja() {
        return datumOtvaranja;
    }

    public void setDatumOtvaranja(LocalDate datumOtvaranja) {
        this.datumOtvaranja = datumOtvaranja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDatumZatvaranja() {
        return datumZatvaranja;
    }

    public void setDatumZatvaranja(LocalDate datumZatvaranja) {
        this.datumZatvaranja = datumZatvaranja;
    }

    public VlasnikSalona getVlasnikSalona() {
        return vlasnikSalona;
    }

    public void setVlasnikSalona(VlasnikSalona vlasnikSalona) {
        this.vlasnikSalona = vlasnikSalona;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

}