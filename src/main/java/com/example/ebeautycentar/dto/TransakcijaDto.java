package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Transakcija;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link Transakcija}
 */
public class TransakcijaDto implements Serializable {
    private final Long id;
    private final String valuta;
    private final String status;
    private final BigDecimal iznos;
    private final Instant datumTransakcije;
    private final Long rezervacijaId;

    public TransakcijaDto(Long id, String valuta, String status, BigDecimal iznos, Instant datumTransakcije, Long rezervacijaId) {
        this.id = id;
        this.valuta = valuta;
        this.status = status;
        this.iznos = iznos;
        this.datumTransakcije = datumTransakcije;
        this.rezervacijaId = rezervacijaId;
    }

    public TransakcijaDto(Transakcija transakcija) {
        this.id = transakcija.getId();
        this.valuta = transakcija.getValuta();
        this.status = transakcija.getStatus();
        this.iznos = transakcija.getIznos();
        this.datumTransakcije = transakcija.getDatumTransakcije();
        this.rezervacijaId = transakcija.getRezervacija().getId();
    }

    public Long getId() {
        return id;
    }

    public String getValuta() {
        return valuta;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getIznos() {
        return iznos;
    }

    public Instant getDatumTransakcije() {
        return datumTransakcije;
    }

    public Long getRezervacijaId() {
        return rezervacijaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransakcijaDto entity = (TransakcijaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.valuta, entity.valuta) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.iznos, entity.iznos) &&
                Objects.equals(this.datumTransakcije, entity.datumTransakcije) &&
                Objects.equals(this.rezervacijaId, entity.rezervacijaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valuta, status, iznos, datumTransakcije, rezervacijaId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "valuta = " + valuta + ", " +
                "status = " + status + ", " +
                "iznos = " + iznos + ", " +
                "datumTransakcije = " + datumTransakcije + ", " +
                "rezervacijaId = " + rezervacijaId + ")";
    }
}