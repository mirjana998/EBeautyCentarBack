package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Zaposleni;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Zaposleni}
 */
public class ZaposleniDto implements Serializable {
    private  Long id;
    private  String ime;
    private  String prezime;
    private  String aktivan;
    private  Long salonId;
    private  Long vlasnikSalonaId;

    @JsonCreator
    public ZaposleniDto(
            @JsonProperty("id") Long id,
            @JsonProperty("ime") String ime,
            @JsonProperty("prezime") String prezime,
            @JsonProperty("aktivan") String aktivan,
            @JsonProperty("salonId") Long salonId,
            @JsonProperty("vlasnikSalonaId") Long vlasnikSalonaId
    ) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.aktivan = aktivan;
        this.salonId = salonId;
        this.vlasnikSalonaId = vlasnikSalonaId;
    }

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