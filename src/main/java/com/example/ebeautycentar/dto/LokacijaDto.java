package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Lokacija;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Lokacija}
 */
public class LokacijaDto implements Serializable {
    private final Long id;
    private final String grad;
    private final String drzava;
    private final String ulica;
    private final String broj;
    private final String postanskiBroj;
    private final String geografskaDuzina;
    private final String geografskaSirina;

    public LokacijaDto(Lokacija lokacija) {
        this.id = lokacija.getId();
        this.grad = lokacija.getGrad();
        this.drzava = lokacija.getDrzava();
        this.ulica = lokacija.getUlica();
        this.broj = lokacija.getBroj();
        this.postanskiBroj = lokacija.getPostanskiBroj();
        this.geografskaDuzina = lokacija.getGeografskaDuzina();
        this.geografskaSirina = lokacija.getGeografskaSirina();
    }

    public Long getId() {
        return id;
    }

    public String getGrad() {
        return grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public String getUlica() {
        return ulica;
    }

    public String getBroj() {
        return broj;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public String getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public String getGeografskaSirina() {
        return geografskaSirina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LokacijaDto entity = (LokacijaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.grad, entity.grad) &&
                Objects.equals(this.drzava, entity.drzava) &&
                Objects.equals(this.ulica, entity.ulica) &&
                Objects.equals(this.broj, entity.broj) &&
                Objects.equals(this.postanskiBroj, entity.postanskiBroj) &&
                Objects.equals(this.geografskaDuzina, entity.geografskaDuzina) &&
                Objects.equals(this.geografskaSirina, entity.geografskaSirina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grad, drzava, ulica, broj, postanskiBroj, geografskaDuzina, geografskaSirina);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "grad = " + grad + ", " +
                "drzava = " + drzava + ", " +
                "ulica = " + ulica + ", " +
                "broj = " + broj + ", " +
                "postanskiBroj = " + postanskiBroj + ", " +
                "geografskaDuzina = " + geografskaDuzina + ", " +
                "geografskaSirina = " + geografskaSirina + ")";
    }
}