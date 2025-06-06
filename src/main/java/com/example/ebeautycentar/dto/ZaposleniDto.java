package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Zaposleni;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Zaposleni}
 */
public class ZaposleniDto implements Serializable {
    private final Long id;
    private final String ime;
    private final String prezime;
    private final String aktivan;
    private final Long salonId;
    private final Long vlasnikSalonaId;

    public ZaposleniDto(Zaposleni zaposleni) {
        this.id = zaposleni.getId();
        this.ime = zaposleni.getIme();
        this.prezime = zaposleni.getPrezime();
        this.aktivan = zaposleni.getAktivan();
        this.salonId = zaposleni.getSalon().getId();
        this.vlasnikSalonaId = zaposleni.getVlasnikSalona().getId();
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getAktivan() {
        return aktivan;
    }

    public Long getSalonId() {
        return salonId;
    }

    public Long getVlasnikSalonaId() {
        return vlasnikSalonaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZaposleniDto entity = (ZaposleniDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.ime, entity.ime) &&
                Objects.equals(this.prezime, entity.prezime) &&
                Objects.equals(this.aktivan, entity.aktivan) &&
                Objects.equals(this.salonId, entity.salonId) &&
                Objects.equals(this.vlasnikSalonaId, entity.vlasnikSalonaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, aktivan, salonId, vlasnikSalonaId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "ime = " + ime + ", " +
                "prezime = " + prezime + ", " +
                "aktivan = " + aktivan + ", " +
                "salonId = " + salonId + ", " +
                "vlasnikSalonaId = " + vlasnikSalonaId + ")";
    }
}