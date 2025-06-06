package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.DanUSedmici;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link DanUSedmici}
 */
public class DanUSedmiciDto implements Serializable {
    private final Integer id;
    private final String naziv;

    public DanUSedmiciDto(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public DanUSedmiciDto(DanUSedmici danUSedmici) {
        this.id = danUSedmici.getId();
        this.naziv = danUSedmici.getNaziv();
    }

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DanUSedmiciDto entity = (DanUSedmiciDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.naziv, entity.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "naziv = " + naziv + ")";
    }
}