package com.example.ebeautycentar.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "rezervacija")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "vrijeme_zakazivanja", nullable = false)
    private Instant vrijemeZakazivanja;

    @ColumnDefault("'I'")
    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @Column(name = "vrijeme_otkazivanja_vlasnik")
    private Instant vrijemeOtkazivanjaVlasnik;

    @Column(name = "vrijeme_otkazivanja_klijent")
    private Instant vrijemeOtkazivanjaKlijent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vlasnik_salona_id")
    private VlasnikSalona vlasnikSalona;

    @Column(name = "termin_pocetka_usluge", nullable = false)
    private Instant terminPocetkaUsluge;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "registrovani_klijent_id", nullable = false)
    private RegistrovaniKlijent registrovaniKlijent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ZaposleniSalonUsluga zaposleniSalonUsluga;

    @Column(name = "`termin_završetka_usluge`", nullable = false)
    private Instant terminZavršetkaUsluge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getVrijemeZakazivanja() {
        return vrijemeZakazivanja;
    }

    public void setVrijemeZakazivanja(Instant vrijemeZakazivanja) {
        this.vrijemeZakazivanja = vrijemeZakazivanja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getVrijemeOtkazivanjaVlasnik() {
        return vrijemeOtkazivanjaVlasnik;
    }

    public void setVrijemeOtkazivanjaVlasnik(Instant vrijemeOtkazivanjaVlasnik) {
        this.vrijemeOtkazivanjaVlasnik = vrijemeOtkazivanjaVlasnik;
    }

    public Instant getVrijemeOtkazivanjaKlijent() {
        return vrijemeOtkazivanjaKlijent;
    }

    public void setVrijemeOtkazivanjaKlijent(Instant vrijemeOtkazivanjaKlijent) {
        this.vrijemeOtkazivanjaKlijent = vrijemeOtkazivanjaKlijent;
    }

    public VlasnikSalona getVlasnikSalona() {
        return vlasnikSalona;
    }

    public void setVlasnikSalona(VlasnikSalona vlasnikSalona) {
        this.vlasnikSalona = vlasnikSalona;
    }

    public Instant getTerminPocetkaUsluge() {
        return terminPocetkaUsluge;
    }

    public void setTerminPocetkaUsluge(Instant terminPocetkaUsluge) {
        this.terminPocetkaUsluge = terminPocetkaUsluge;
    }

    public RegistrovaniKlijent getRegistrovaniKlijent() {
        return registrovaniKlijent;
    }

    public void setRegistrovaniKlijent(RegistrovaniKlijent registrovaniKlijent) {
        this.registrovaniKlijent = registrovaniKlijent;
    }

    public ZaposleniSalonUsluga getZaposleniSalonUsluga() {
        return zaposleniSalonUsluga;
    }

    public void setZaposleniSalonUsluga(ZaposleniSalonUsluga zaposleniSalonUsluga) {
        this.zaposleniSalonUsluga = zaposleniSalonUsluga;
    }

    public Instant getTerminZavršetkaUsluge() {
        return terminZavršetkaUsluge;
    }

    public void setTerminZavršetkaUsluge(Instant terminZavršetkaUsluge) {
        this.terminZavršetkaUsluge = terminZavršetkaUsluge;
    }

}