package com.example.ebeautycentar.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class TransakcijaStripeDto {

    private Long sesijaId;
    private Long rezervacijaId;
    private BigDecimal ukupanIznos;
    private String valuta;
    private String status;
    private String emailKlijenta;
    private Instant datumTransakcije;

    public TransakcijaStripeDto(Long sesijaId, Long rezervacijaId, BigDecimal ukupanIznos, String valuta, String status, String emailKlijenta, Instant datumTransakcije) {
        this.sesijaId = sesijaId;
        this.rezervacijaId = rezervacijaId;
        this.ukupanIznos = ukupanIznos;
        this.valuta = valuta;
        this.status = status;
        this.emailKlijenta = emailKlijenta;
        this.datumTransakcije = datumTransakcije;
    }

    public Long getSesijaId() {
        return sesijaId;
    }

    public void setSesijaId(Long sesijaId) {
        this.sesijaId = sesijaId;
    }

    public Long getRezervacijaId() {
        return rezervacijaId;
    }

    public void setRezervacijaId(Long rezervacijaId) {
        this.rezervacijaId = rezervacijaId;
    }

    public BigDecimal getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(BigDecimal ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmailKlijenta() {
        return emailKlijenta;
    }

    public void setEmailKlijenta(String emailKlijenta) {
        this.emailKlijenta = emailKlijenta;
    }

    public Instant getDatumTransakcije() {
        return datumTransakcije;
    }

    public void setDatumTransakcije(Instant datumTransakcije) {
        this.datumTransakcije = datumTransakcije;
    }

    @Override
    public String toString() {
        return "TransakcijaStripeDto{" +
                "sesijaId=" + sesijaId +
                ", rezervacijaId=" + rezervacijaId +
                ", ukupanIznos=" + ukupanIznos +
                ", valuta='" + valuta + '\'' +
                ", status='" + status + '\'' +
                ", emailKlijenta='" + emailKlijenta + '\'' +
                ", datumTransakcije=" + datumTransakcije +
                '}';
    }
}
