package com.example.ebeautycentar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "korisnik")
public class Korisnik {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ime", nullable = false, length = 45)
    private String ime;

    @Column(name = "prezime", nullable = false, length = 45)
    private String prezime;

    @Column(name = "broj_telefona", nullable = false, length = 20)
    private String brojTelefona;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "korisnicko_ime", nullable = false, length = 45)
    private String korisnickoIme;

    @Column(name = "lozinka", nullable = false, length = 45)
    private String lozinka;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}