package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Salon;

import java.io.Serializable;
import java.time.Instant;

public class RezervacijaKlijentDto implements Serializable {

    private long id;
    private SalonDto salon;
    private String nazivUsluge;
    private ZaposleniDto zaposleni;
    private Instant vrijemePocetka;
    private String statusRezervacije;
    private String clerkUserId;
    private OcjenaPruzeneUslugeDto ocjena;

    public RezervacijaKlijentDto() {}

    public RezervacijaKlijentDto(long id, SalonDto salon, String nazivUsluge, ZaposleniDto zaposleni, Instant vrijemePocetka, String clerkUserId, String statusRezervacije, OcjenaPruzeneUslugeDto ocjena) {
        this.id = id;
        this.salon = salon;
        this.nazivUsluge = nazivUsluge;
        this.zaposleni = zaposleni;
        this.vrijemePocetka = vrijemePocetka;
        this.statusRezervacije = statusRezervacije;
        this.clerkUserId = clerkUserId;
        this.ocjena = ocjena;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SalonDto getSalon() {
        return salon;
    }

    public void setSalon(SalonDto salon) {
        this.salon = salon;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public ZaposleniDto getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(ZaposleniDto zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Instant getVrijemePocetka() {
        return vrijemePocetka;
    }

    public void setVrijemePocetka(Instant vrijemePocetka) {
        this.vrijemePocetka = vrijemePocetka;
    }

    public String getStatusRezervacije() {
        return statusRezervacije;
    }

    public void setStatusRezervacije(String statusRezervacije) {
        this.statusRezervacije = statusRezervacije;
    }


    public String getClerkUserId() {
        return clerkUserId;
    }

    public void setClerkUserId(String clerkUserId) {
        this.clerkUserId = clerkUserId;
    }

    public OcjenaPruzeneUslugeDto getOcjena() {
        return ocjena;
    }

    public void setOcjena(OcjenaPruzeneUslugeDto ocjena) {
        this.ocjena = ocjena;
    }
}
