package com.example.ebeautycentar.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "notifikacija")
public class Notifikacija {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sadrzaj", nullable = false, length = 1000)
    private String sadrzaj;

    @Column(name = "vrijeme_kreiranja", nullable = false)
    private Instant vrijemeKreiranja;

    @ColumnDefault("'A'")
    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

}