package com.example.ebeautycentar.dto;

import java.io.Serializable;
import java.time.LocalDate;


public class SalonKreirajDTO implements Serializable {

    private Long id;
    private String naziv;
    private String email;
    private String brojTelefona;
    private String tipNaziv;
    private String vlasnikIme;
    private String vlasnikPrezime;
    private String ulica;
    private String broj;
    private String grad;
    private String drzava;
    private String postanskiBroj;
    private LocalDate datumOtvaranja;
    private LocalDate datumZatvaranja;
    private String slikaNaziv;

    public  SalonKreirajDTO(){}

    public SalonKreirajDTO(Long id, String naziv, String email, String brojTelefona, String tipNaziv, String vlasnikIme, String ulica, String vlasnikPrezime, String broj, String grad, String drzava, String postanskiBroj, LocalDate datumOtvaranja, LocalDate datumZatvaranja, String slikaNaziv) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.tipNaziv = tipNaziv;
        this.vlasnikIme = vlasnikIme;
        this.ulica = ulica;
        this.vlasnikPrezime = vlasnikPrezime;
        this.broj = broj;
        this.grad = grad;
        this.drzava = drzava;
        this.postanskiBroj = postanskiBroj;
        this.datumOtvaranja = datumOtvaranja;
        this.datumZatvaranja = datumZatvaranja;
        this.slikaNaziv = slikaNaziv;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public void setTipNaziv(String tipNaziv) {
        this.tipNaziv = tipNaziv;
    }

    public void setVlasnikIme(String vlasnikIme) {
        this.vlasnikIme = vlasnikIme;
    }

    public void setVlasnikPrezime(String vlasnikPrezime) {
        this.vlasnikPrezime = vlasnikPrezime;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public void setDatumOtvaranja(LocalDate datumOtvaranja) {
        this.datumOtvaranja = datumOtvaranja;
    }

    public void setDatumZatvaranja(LocalDate datumZatvaranja) {
        this.datumZatvaranja = datumZatvaranja;
    }

    public void setSlikaNaziv(String slikaNaziv) {
        this.slikaNaziv = slikaNaziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public String getTipNaziv() {
        return tipNaziv;
    }

    public String getVlasnikIme() {
        return vlasnikIme;
    }

    public String getVlasnikPrezime() {
        return vlasnikPrezime;
    }

    public String getBroj() {
        return broj;
    }

    public String getUlica() {
        return ulica;
    }

    public String getGrad() {
        return grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public LocalDate getDatumOtvaranja() {
        return datumOtvaranja;
    }

    public String getSlikaNaziv() {
        return slikaNaziv;
    }

    public LocalDate getDatumZatvaranja() {
        return datumZatvaranja;
    }
}
