package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Novost;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link Novost}
 */
public class NovostDto implements Serializable {
    private final Long id;
    private final Long salonId;
    private final String naslov;
    private final String sadrzaj;
    private final Instant vrijemeKreiranja;
    private final String status;
    private final Long vlasnikSalonaId;

    public NovostDto(Novost novost) {
        this.id = novost.getId();
        this.salonId = novost.getSalon().getId();
        this.naslov = novost.getNaslov();
        this.sadrzaj = novost.getSadrzaj();
        this.status = novost.getStatus();
        this.vrijemeKreiranja = novost.getVrijemeKreiranja();
        this.vlasnikSalonaId = novost.getVlasnikSalona().getId();
    }

    public Long getId() {
        return id;
    }

    public Long getSalonId() {
        return salonId;
    }

    public String getNaslov() {
        return naslov;
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

    public Long getVlasnikSalonaId() {
        return vlasnikSalonaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NovostDto entity = (NovostDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.salonId, entity.salonId) &&
                Objects.equals(this.naslov, entity.naslov) &&
                Objects.equals(this.sadrzaj, entity.sadrzaj) &&
                Objects.equals(this.vrijemeKreiranja, entity.vrijemeKreiranja) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.vlasnikSalonaId, entity.vlasnikSalonaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salonId, naslov, sadrzaj, vrijemeKreiranja, status, vlasnikSalonaId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "salonId = " + salonId + ", " +
                "naslov = " + naslov + ", " +
                "sadrzaj = " + sadrzaj + ", " +
                "vrijemeKreiranja = " + vrijemeKreiranja + ", " +
                "status = " + status + ", " +
                "vlasnikSalonaId = " + vlasnikSalonaId + ")";
    }
}