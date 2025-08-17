package com.example.ebeautycentar.dto;

import java.io.Serializable;

public class ClerkRegistrovaniKlijentDto implements Serializable {

    private String ime;
    private String prezime;
    private String email;
    private String korisnickoIme;
    private String brojTelefona;
    private String clerkUserId; // ✅ NOVO POLJE

    public ClerkRegistrovaniKlijentDto() {
    }

    public ClerkRegistrovaniKlijentDto(String ime, String prezime, String email,
                                       String korisnickoIme, String brojTelefona,
                                       String clerkUserId) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.brojTelefona = brojTelefona;
        this.clerkUserId = clerkUserId;
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

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getClerkUserId() {
        return clerkUserId;
    }

    public void setClerkUserId(String clerkUserId) {
        this.clerkUserId = clerkUserId;
    }
}
