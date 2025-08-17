package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Korisnik;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Korisnik}
 */
public class KorisnikDto implements Serializable {
    private final Long id;
    private final String ime;
    private final String prezime;
    private final String brojTelefona;
    private final String email;
    private final String korisnickoIme;
    private final String lozinka;
    private final String status;
    private final String clerkUserId; // ✅ novo polje

    public KorisnikDto(Long id, String ime, String prezime, String brojTelefona,
                       String email, String korisnickoIme, String lozinka,
                       String status, String clerkUserId) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.status = status;
        this.clerkUserId = clerkUserId;
    }

    public KorisnikDto(Korisnik korisnik) {
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.brojTelefona = korisnik.getBrojTelefona();
        this.email = korisnik.getEmail();
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.lozinka = korisnik.getLozinka();
        this.status = korisnik.getStatus();
        this.clerkUserId = korisnik.getClerkUserId(); // ✅ mapiranje iz entiteta
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KorisnikDto entity = (KorisnikDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.ime, entity.ime) &&
                Objects.equals(this.prezime, entity.prezime) &&
                Objects.equals(this.brojTelefona, entity.brojTelefona) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.korisnickoIme, entity.korisnickoIme) &&
                Objects.equals(this.lozinka, entity.lozinka) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.clerkUserId, entity.clerkUserId);
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
