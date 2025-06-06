package com.example.ebeautycentar.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "radno_vrijeme")
public class RadnoVrijeme {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dan_u_sedmici_id", nullable = false)
    private DanUSedmici danUSedmici;

    @Column(name = "pocetak_radno_vrijeme")
    private LocalTime pocetakRadnoVrijeme;

    @Column(name = "kraj_radno_vrijeme")
    private LocalTime krajRadnoVrijeme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "salon_id", nullable = false)
    private Salon salon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DanUSedmici getDanUSedmici() {
        return danUSedmici;
    }

    public void setDanUSedmici(DanUSedmici danUSedmici) {
        this.danUSedmici = danUSedmici;
    }

    public LocalTime getPocetakRadnoVrijeme() {
        return pocetakRadnoVrijeme;
    }

    public void setPocetakRadnoVrijeme(LocalTime pocetakRadnoVrijeme) {
        this.pocetakRadnoVrijeme = pocetakRadnoVrijeme;
    }

    public LocalTime getKrajRadnoVrijeme() {
        return krajRadnoVrijeme;
    }

    public void setKrajRadnoVrijeme(LocalTime krajRadnoVrijeme) {
        this.krajRadnoVrijeme = krajRadnoVrijeme;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

}