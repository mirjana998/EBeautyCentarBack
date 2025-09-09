package com.example.ebeautycentar.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transakcija")
public class Transakcija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "valuta", nullable = false, length = 10)
    private String valuta;

    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @Column(name = "iznos", nullable = false, precision = 10)
    private BigDecimal iznos;

    @Column(name = "datum_transakcije", nullable = false)
    private Instant datumTransakcije;

    @Column(name = "session_id", nullable = true)
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rezervacija_id", nullable = false)
    private Rezervacija rezervacija;

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getIznos() {
        return iznos;
    }

    public void setIznos(BigDecimal iznos) {
        this.iznos = iznos;
    }

    public Instant getDatumTransakcije() {
        return datumTransakcije;
    }

    public void setDatumTransakcije(Instant datumTransakcije) {
        this.datumTransakcije = datumTransakcije;
    }

}