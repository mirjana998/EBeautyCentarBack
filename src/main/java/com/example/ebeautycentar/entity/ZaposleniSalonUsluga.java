package com.example.ebeautycentar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "zaposleni_salon_usluga")
public class ZaposleniSalonUsluga {
    @EmbeddedId
    private ZaposleniSalonUslugaId id;

    @MapsId("zaposleniId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zaposleni_id", nullable = false)
    private Zaposleni zaposleni;

    @MapsId("salonUslugaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "salon_usluga_id", nullable = false)
    private SalonUsluga salonUsluga;

    public ZaposleniSalonUslugaId getId() {
        return id;
    }

    public void setId(ZaposleniSalonUslugaId id) {
        this.id = id;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public SalonUsluga getSalonUsluga() {
        return salonUsluga;
    }

    public void setSalonUsluga(SalonUsluga salonUsluga) {
        this.salonUsluga = salonUsluga;
    }

}