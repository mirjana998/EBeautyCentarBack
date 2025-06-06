package com.example.ebeautycentar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "registrovani_klijent")
public class RegistrovaniKlijent {
    @Id
    @Column(name = "korisnik_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    @Column(name = "broj_termina", nullable = false)
    private Integer brojTermina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Integer getBrojTermina() {
        return brojTermina;
    }

    public void setBrojTermina(Integer brojTermina) {
        this.brojTermina = brojTermina;
    }

}