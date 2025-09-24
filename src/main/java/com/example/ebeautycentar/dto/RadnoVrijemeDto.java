package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.RadnoVrijeme;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

/**
 * DTO for {@link RadnoVrijeme}
 */
public class RadnoVrijemeDto implements Serializable {
    private  Long id;
    private  DanUSedmiciDto danUSedmici;
    private  LocalTime pocetakRadnoVrijeme;
    private  LocalTime krajRadnoVrijeme;
    private Long salonId;

    public RadnoVrijemeDto() {
    }

    public RadnoVrijemeDto(RadnoVrijeme radnoVrijeme) {
        this.id = radnoVrijeme.getId();
        this.danUSedmici = new DanUSedmiciDto(radnoVrijeme.getDanUSedmici());
        this.pocetakRadnoVrijeme = radnoVrijeme.getPocetakRadnoVrijeme();
        this.krajRadnoVrijeme = radnoVrijeme.getKrajRadnoVrijeme();
        this.salonId = radnoVrijeme.getSalon().getId();
    }

    public Long getId() {
        return id;
    }

    public LocalTime getPocetakRadnoVrijeme() {
        return pocetakRadnoVrijeme;
    }

    public LocalTime getKrajRadnoVrijeme() {
        return krajRadnoVrijeme;
    }

    public Long getSalonId() {
        return salonId;
    }

    public DanUSedmiciDto getDanUSedmici() {
        return danUSedmici;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadnoVrijemeDto that = (RadnoVrijemeDto) o;
        return Objects.equals(id, that.id) && Objects.equals(danUSedmici, that.danUSedmici) && Objects.equals(pocetakRadnoVrijeme, that.pocetakRadnoVrijeme) && Objects.equals(krajRadnoVrijeme, that.krajRadnoVrijeme) && Objects.equals(salonId, that.salonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, danUSedmici, pocetakRadnoVrijeme, krajRadnoVrijeme, salonId);
    }

    @Override
    public String toString() {
        return "RadnoVrijemeDto{" +
                "id=" + id +
                ", danUSedmici=" + danUSedmici +
                ", pocetakRadnoVrijeme=" + pocetakRadnoVrijeme +
                ", krajRadnoVrijeme=" + krajRadnoVrijeme +
                ", salonId=" + salonId +
                '}';
    }
}