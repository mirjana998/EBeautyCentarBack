package com.example.ebeautycentar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lokacija")
public class Lokacija {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "grad", nullable = false, length = 100)
    private String grad;

    @Column(name = "drzava", nullable = false, length = 100)
    private String drzava;

    @Column(name = "ulica", nullable = false, length = 100)
    private String ulica;

    @Column(name = "broj", nullable = false, length = 10)
    private String broj;

    @Column(name = "postanski_broj", nullable = false, length = 45)
    private String postanskiBroj;

    @Column(name = "geografska_duzina", length = 100)
    private String geografskaDuzina;

    @Column(name = "geografska_sirina", length = 100)
    private String geografskaSirina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(String geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public String getGeografskaSirina() {
        return geografskaSirina;
    }

    public void setGeografskaSirina(String geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

}