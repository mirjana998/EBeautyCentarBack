package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Tip;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Tip}
 */
public class TipDto implements Serializable {
    private final Integer id;
    private final String naziv;
    private final String status;

    public TipDto(Tip tip) {
        this.id = tip.getId();
        this.naziv = tip.getNaziv();
        this.status = tip.getStatus();
    }

    public Integer getId() {
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
        TipDto entity = (TipDto) o;
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