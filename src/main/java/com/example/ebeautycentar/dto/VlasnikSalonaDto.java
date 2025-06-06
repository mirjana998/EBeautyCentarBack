package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.VlasnikSalona;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link VlasnikSalona}
 */
public class VlasnikSalonaDto implements Serializable {
    private final Long id;
    private final KorisnikDto korisnik;

    public VlasnikSalonaDto(VlasnikSalona vlasnikSalona) {
        this.id = vlasnikSalona.getId();
        this.korisnik = new KorisnikDto(vlasnikSalona.getKorisnik());
    }

    public Long getId() {
        return id;
    }

    public KorisnikDto getKorisnik() {
        return korisnik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VlasnikSalonaDto entity = (VlasnikSalonaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.korisnik, entity.korisnik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, korisnik);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "korisnik = " + korisnik + ")";
    }
}