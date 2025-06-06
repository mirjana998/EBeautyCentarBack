package com.example.ebeautycentar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrator")
public class Administrator {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "korisnicko_ime", nullable = false, length = 45)
    private String korisnickoIme;

    @Column(name = "lozinka", nullable = false, length = 45)
    private String lozinka;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

}