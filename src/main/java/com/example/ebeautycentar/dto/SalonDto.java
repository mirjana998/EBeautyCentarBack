package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Lokacija;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.entity.Tip;
import com.example.ebeautycentar.entity.VlasnikSalona;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link Salon}
 */
public class SalonDto implements Serializable {
    private final Long id;
    private final String naziv;
    private final String email;
    private final String brojTelefona;
    private final Tip tip;
    private final LocalDate datumOtvaranja;
    private final String status;
    private final LocalDate datumZatvaranja;
    private final VlasnikSalona vlasnikSalona;
    private final Lokacija lokacija;

    public SalonDto(Salon salon) {
        this.id = salon.getId();
        this.naziv = salon.getNaziv();
        this.email = salon.getEmail();
        this.brojTelefona = salon.getBrojTelefona();
        this.tip = salon.getTip();
        this.datumOtvaranja = salon.getDatumOtvaranja();
        this.status = salon.getStatus();
        this.datumZatvaranja = salon.getDatumZatvaranja();
        this.lokacija = salon.getLokacija();
        this.vlasnikSalona = salon.getVlasnikSalona();
    }


    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getEmail() {
        return email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public Tip getTip() {
        return tip;
    }

    public LocalDate getDatumOtvaranja() {
        return datumOtvaranja;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDatumZatvaranja() {
        return datumZatvaranja;
    }

    public VlasnikSalona getVlasnikSalona() {
        return vlasnikSalona;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalonDto entity = (SalonDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.naziv, entity.naziv) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.brojTelefona, entity.brojTelefona) &&
                Objects.equals(this.tip, entity.tip) &&
                Objects.equals(this.datumOtvaranja, entity.datumOtvaranja) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.datumZatvaranja, entity.datumZatvaranja) &&
                Objects.equals(this.vlasnikSalona, entity.vlasnikSalona) &&
                Objects.equals(this.lokacija, entity.lokacija);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, email, brojTelefona, tip, datumOtvaranja, status, datumZatvaranja, vlasnikSalona, lokacija);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "naziv = " + naziv + ", " +
                "email = " + email + ", " +
                "brojTelefona = " + brojTelefona + ", " +
                "tip = " + tip + ", " +
                "datumOtvaranja = " + datumOtvaranja + ", " +
                "status = " + status + ", " +
                "datumZatvaranja = " + datumZatvaranja + ", " +
                "vlasnikSalona = " + vlasnikSalona + ", " +
                "lokacija = " + lokacija + ")";
    }
}