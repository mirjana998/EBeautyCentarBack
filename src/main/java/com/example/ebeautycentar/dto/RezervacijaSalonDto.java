package com.example.ebeautycentar.dto;

import java.time.Instant;

public class RezervacijaSalonDto {
    private Long id;
    private String imeKlijenta;
    private String prezimeKlijenta;
    private String nazivUsluge;
    private Double cijena;
    private String status;
    private String valuta;
    private Instant terminPocetkUsluge;
    private Instant terminZavrsetkaUsluge;
    private Long korisnikId;

    public RezervacijaSalonDto() {
    }

    public RezervacijaSalonDto(Long id, String imeKlijenta, String prezimeKlijenta, String nazivUsluge, Double cijena, String valuta, String status, Instant terminPocetkUsluge, Instant terminZavrsetkaUsluge, Long korisnikId) {
        this.imeKlijenta = imeKlijenta;
        this.prezimeKlijenta = prezimeKlijenta;
        this.nazivUsluge = nazivUsluge;
        this.cijena = cijena;
        this.id = id;
        this.valuta = valuta;
        this.status = status;
        this.terminPocetkUsluge = terminPocetkUsluge;
        this.terminZavrsetkaUsluge = terminZavrsetkaUsluge;
        this.korisnikId = korisnikId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImeKlijenta() {
        return imeKlijenta;
    }

    public void setImeKlijenta(String imeKlijenta) {
        this.imeKlijenta = imeKlijenta;
    }

    public String getPrezimeKlijenta() {
        return prezimeKlijenta;
    }

    public void setPrezimeKlijenta(String prezimeKlijenta) {
        this.prezimeKlijenta = prezimeKlijenta;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public Instant getTerminPocetkUsluge() {
        return terminPocetkUsluge;
    }

    public void setTerminPocetkUsluge(Instant terminPocetkUsluge) {
        this.terminPocetkUsluge = terminPocetkUsluge;
    }

    public Instant getTerminZavrsetkaUsluge() {
        return terminZavrsetkaUsluge;
    }

    public void setTerminZavrsetkaUsluge(Instant terminZavrsetkaUsluge) {
        this.terminZavrsetkaUsluge = terminZavrsetkaUsluge;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }
}
