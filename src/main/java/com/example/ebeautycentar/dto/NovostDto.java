package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Novost;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link Novost}
 */
public class NovostDto implements Serializable {

    private Long id;
    private Long salonId;
    private String naslov;
    private String sadrzaj;
    private Instant vrijemeKreiranja;
    private String status;
    private Long vlasnikSalonaId;

    public NovostDto(Novost novost) {
        this.id = novost.getId();
        this.salonId = novost.getSalon().getId();
        this.naslov = novost.getNaslov();
        this.sadrzaj = novost.getSadrzaj();
        this.status = novost.getStatus();
        this.vrijemeKreiranja = novost.getVrijemeKreiranja();
        this.vlasnikSalonaId = novost.getVlasnikSalona().getId();
    }

    public NovostDto() {
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSalonId() {
        return salonId;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Instant getVrijemeKreiranja() {
        return vrijemeKreiranja;
    }

    public void setVrijemeKreiranja(Instant vrijemeKreiranja) {
        this.vrijemeKreiranja = vrijemeKreiranja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVlasnikSalonaId() {
        return vlasnikSalonaId;
    }

    public void setVlasnikSalonaId(Long vlasnikSalonaId) {
        this.vlasnikSalonaId = vlasnikSalonaId;
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