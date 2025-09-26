package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.VlasnikSalona;

import java.io.Serializable;

public class VlasnikSalonaUpdateDto implements Serializable {
    private final Long vlasnikId;
    private final String ime;
    private final String prezime;
    private final String brojTelefona;
    private final String email;

    public VlasnikSalonaUpdateDto(VlasnikSalona vlasnik) {
        this(
                vlasnik.getId(),
                vlasnik.getKorisnik().getIme(),
                vlasnik.getKorisnik().getPrezime(),
                vlasnik.getKorisnik().getBrojTelefona(),
                vlasnik.getKorisnik().getEmail()
        );
    }

    public VlasnikSalonaUpdateDto(Long vlasnikId, String ime, String prezime, String brojTelefona, String email) {
        this.vlasnikId = vlasnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
    }

    public Long getId() {
        return vlasnikId;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getIme() {
        return ime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public String getEmail() {
        return email;
    }
}
