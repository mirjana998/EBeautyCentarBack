package com.example.ebeautycentar.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "zaposleni")
public class Zaposleni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ime", nullable = false, length = 45)
    private String ime;

    @Column(name = "prezime", nullable = false, length = 45)
    private String prezime;

    @ColumnDefault("'A'")
    @Column(name = "aktivan", nullable = false, length = 1)
    private String aktivan;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "salon_id", nullable = false)
    private Salon salon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vlasnik_salona_id", nullable = false)
    private VlasnikSalona vlasnikSalona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAktivan() {
        return aktivan;
    }

    public void setAktivan(String aktivan) {
        this.aktivan = aktivan;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public VlasnikSalona getVlasnikSalona() {
        return vlasnikSalona;
    }

    public void setVlasnikSalona(VlasnikSalona vlasnikSalona) {
        this.vlasnikSalona = vlasnikSalona;
    }

}