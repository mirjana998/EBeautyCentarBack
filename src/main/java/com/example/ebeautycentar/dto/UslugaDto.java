package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Usluga;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Usluga}
 */
public class UslugaDto implements Serializable {
    private final Long id;
    private final String naziv;
    private final String status;

    public UslugaDto(Usluga usluga) {
        this.id = usluga.getId();
        this.naziv = usluga.getNaziv();
        this.status = usluga.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UslugaDto entity = (UslugaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.naziv, entity.naziv) &&
                Objects.equals(this.status, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "naziv = " + naziv + ", " +
                "status = " + status + ")";
    }
}