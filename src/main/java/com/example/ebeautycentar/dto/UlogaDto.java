package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Uloga;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Uloga}
 */
public class UlogaDto implements Serializable {
    private final Integer id;
    private final String naziv;
    private final String opis;
    private final String status;

    public UlogaDto(Integer id, String naziv, String opis, String status) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UlogaDto entity = (UlogaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.naziv, entity.naziv) &&
                Objects.equals(this.opis, entity.opis) &&
                Objects.equals(this.status, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, opis, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "naziv = " + naziv + ", " +
                "opis = " + opis + ", " +
                "status = " + status + ")";
    }
}