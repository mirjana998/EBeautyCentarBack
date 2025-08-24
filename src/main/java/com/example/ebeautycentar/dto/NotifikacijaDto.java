package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Notifikacija;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link Notifikacija}
 */
public class NotifikacijaDto implements Serializable {
    private final Long id;
    private final String sadrzaj;
    private final Instant vrijemeKreiranja;
    private final String status;
    private final Long korisnikId;

    @JsonCreator
    public NotifikacijaDto(
            @JsonProperty("id") Long id,
            @JsonProperty("sadrzaj") String sadrzaj,
            @JsonProperty("vrijemeKreiranja") Instant vrijemeKreiranja,
            @JsonProperty("status") String status,
            @JsonProperty("korisnikId") Long korisnikId
    ) {
        this.id = id;
        this.sadrzaj =sadrzaj;
        this.korisnikId = korisnikId;
        this.status = status;
        this.vrijemeKreiranja = vrijemeKreiranja;
    }


    public NotifikacijaDto(Notifikacija notifikacija) {
        this.id = notifikacija.getId();
        this.sadrzaj = notifikacija.getSadrzaj();
        this.korisnikId = notifikacija.getKorisnik().getId();
        this.status = notifikacija.getStatus();
        this.vrijemeKreiranja = notifikacija.getVrijemeKreiranja();
    }

    public Long getId() {
        return id;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public Instant getVrijemeKreiranja() {
        return vrijemeKreiranja;
    }

    public String getStatus() {
        return status;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotifikacijaDto entity = (NotifikacijaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.sadrzaj, entity.sadrzaj) &&
                Objects.equals(this.vrijemeKreiranja, entity.vrijemeKreiranja) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.korisnikId, entity.korisnikId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sadrzaj, vrijemeKreiranja, status, korisnikId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "sadrzaj = " + sadrzaj + ", " +
                "vrijemeKreiranja = " + vrijemeKreiranja + ", " +
                "status = " + status + ", " +
                "korisnikId = " + korisnikId + ")";
    }
}