package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Administrator;

import java.io.Serializable;
import java.util.Objects;

public class AdministratorDto implements Serializable {
    private final Integer id;
    private String korisnickoIme;

    public AdministratorDto(Administrator administrator) {
        this.id = administrator.getId();
        this.korisnickoIme = administrator.getKorisnickoIme();
    }

    public Integer getId() {return id;}
    public String getKorisnickoIme() {
        return korisnickoIme;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministratorDto entity = (AdministratorDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.korisnickoIme, entity.korisnickoIme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, korisnickoIme);
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "korisnicko_ime = " + korisnickoIme + ")";
    }
}
