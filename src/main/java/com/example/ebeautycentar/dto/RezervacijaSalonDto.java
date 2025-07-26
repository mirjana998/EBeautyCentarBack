package com.example.ebeautycentar.dto;

public class RezervacijaSalonDto {
    private String imeKlijenta;
    private String prezimeKlijenta;
    private String nazivUsluge;
    private Double cijena;

    public RezervacijaSalonDto() {
    }

    public RezervacijaSalonDto(String imeKlijenta, String prezimeKlijenta, String nazivUsluge, Double cijena) {
        this.imeKlijenta = imeKlijenta;
        this.prezimeKlijenta = prezimeKlijenta;
        this.nazivUsluge = nazivUsluge;
        this.cijena = cijena;
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
}
