package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Korisnik;

import java.util.Objects;

public class KorisnikAuthDto {

    private  Long id;
    private  String ime;
    private  String prezime;
    private  String brojTelefona;
    private  String email;
    private String lozinka;
    private  String korisnickoIme;
    private  String status;
    private  String clerkUserId;
    private String token;

    public KorisnikAuthDto(){}

    public KorisnikAuthDto(Korisnik korisnik) {
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.brojTelefona = korisnik.getBrojTelefona();
        this.email = korisnik.getEmail();
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.lozinka = korisnik.getLozinka();
        this.status = korisnik.getStatus();
        this.clerkUserId = korisnik.getClerkUserId();
        this.token = "";
    }


    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getStatus() {
        return status;
    }

    public String getClerkUserId() {
        return clerkUserId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, brojTelefona, email, korisnickoIme, lozinka, status, clerkUserId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "ime = " + ime + ", " +
                "prezime = " + prezime + ", " +
                "brojTelefona = " + brojTelefona + ", " +
                "email = " + email + ", " +
                "korisnickoIme = " + korisnickoIme + ", " +
                "lozinka = " + lozinka + ", " +
                "status = " + status + ", " +
                "clerkUserId = " + clerkUserId + ")";
    }
}
