package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Slika;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Slika}
 */
public class SlikaDto implements Serializable {
    private final Long id;
    private final String naziv;
    private final Long salonId;
    private final String vrsta;
    private final Long uslugaId;
    private final String status;
    private final byte[] slika;

    public SlikaDto(Slika slika) {
        this.id = slika.getId();
        this.naziv = slika.getNaziv();
        this.salonId = slika.getSalon().getId();
        this.vrsta = slika.getVrsta();
        this.uslugaId = slika.getUsluga().getId();
        this.status = slika.getStatus();
        this.slika = slika.getSlika();
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public Long getSalonId() {
        return salonId;
    }

    public String getVrsta() {
        return vrsta;
    }

    public Long getUslugaId() {
        return uslugaId;
    }

    public String getStatus() {
        return status;
    }

    public byte[] getSlika() {
        return slika;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlikaDto entity = (SlikaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.naziv, entity.naziv) &&
                Objects.equals(this.salonId, entity.salonId) &&
                Objects.equals(this.vrsta, entity.vrsta) &&
                Objects.equals(this.uslugaId, entity.uslugaId) &&
                Objects.equals(this.status, entity.status)&&
                Objects.equals(this.slika, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, salonId, vrsta, uslugaId, status,slika);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "naziv = " + naziv + ", " +
                "salonId = " + salonId + ", " +
                "vrsta = " + vrsta + ", " +
                "uslugaId = " + uslugaId + ", " +
                "status = " + status +
                "slika= "+slika+")";
    }
}