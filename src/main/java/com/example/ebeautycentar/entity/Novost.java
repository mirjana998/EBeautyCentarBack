package com.example.ebeautycentar.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "novost")
public class Novost {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "salon_id", nullable = false)
    private Salon salon;

    @Column(name = "naslov", nullable = false)
    private String naslov;

    @Column(name = "sadrzaj", nullable = false, length = 1000)
    private String sadrzaj;

    @Column(name = "vrijeme_kreiranja", nullable = false)
    private Instant vrijemeKreiranja;

    @ColumnDefault("'A'")
    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vlasnik_salona_id", nullable = false)
    private VlasnikSalona vlasnikSalona;

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

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Instant getVrijemeKreiranja() {
        return vrijemeKreiranja;
    }

    public void setVrijemeKreiranja(Instant vrijemeKreiranja) {
        this.vrijemeKreiranja = vrijemeKreiranja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VlasnikSalona getVlasnikSalona() {
        return vlasnikSalona;
    }

    public void setVlasnikSalona(VlasnikSalona vlasnikSalona) {
        this.vlasnikSalona = vlasnikSalona;
    }

}